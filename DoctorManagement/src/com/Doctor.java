package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Doctor {

	// A common method to connect to the DB

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doctor?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertDoctor(String doctorname, String doctorphone, String doctorspecialty, String doctorexperience) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into doctors   "
					+ "(`doctorid`,`doctorname`,`doctorphone`,`doctorspecialty`,`doctorexperience`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, doctorname);
			preparedStmt.setString(3, doctorphone);
			preparedStmt.setString(4, doctorspecialty);
			preparedStmt.setString(5, doctorexperience);
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newDoctors = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the doctor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDoctors() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Doctorid</th><th>Doctorname</th><th>Doctorphone</th><th>Doctorspecialty</th><th>Doctorexperience</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from doctors";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {

				String doctorid = Integer.toString(rs.getInt("doctorid"));
				String doctorname = rs.getString("doctorname");
				String doctorphone = Integer.toString(rs.getInt("doctorphone"));
				String doctorspecialty = rs.getString("doctorspecialty");
				String doctorexperience = rs.getString("doctorexperience");

				// Add into the html table

				output += "<tr><td><input id='hidDoctorIDUpdate' name='hidDoctorIDUpdate' type='hidden' value='"
						+ doctorid + "'>" + doctorid + "</td>";
				output += "<td>" + doctorname + "</td>";
				output += "<td>" + doctorphone + "</td>";
				output += "<td>" + doctorspecialty + "</td>";
				output += "<td>" + doctorexperience + "</td>";

				// buttons

				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-doctorid='"
						+ doctorid + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctors.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateDoctor(String doctorid, String doctorname, String doctorphone,  String doctorspecialty, String doctorexperience)
			 {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctors SET doctorname=?,doctorphone=?,doctorspecialty=?,doctorexperience=?"
					+ "WHERE doctorid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			// preparedStmt.setString(1, doctorid);
			preparedStmt.setString(1, doctorname);
			preparedStmt.setString(2, doctorphone);
			preparedStmt.setString(3, doctorspecialty);
			preparedStmt.setString(4, doctorexperience);
			preparedStmt.setInt(5, Integer.parseInt(doctorid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			 String newDoctors = readDoctors();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newDoctors + "\"}";
			 
			 
		} catch (Exception e) {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the doctor.\"}";
			 System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String doctorid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctors where doctorid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(doctorid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newDoctors = readDoctors();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newDoctors + "\"}";
		} catch (Exception e) {
			
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the doctor.\"}";
			 System.err.println(e.getMessage()); 
		}
		return output;
	}
}
