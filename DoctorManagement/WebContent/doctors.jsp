<%@page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/doctors.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Doctor Management</h1>

				<form id="formDoctor" name="formDoctor">
					Doctor Name: <input id="doctorname" name="doctorname"
						type="text" class="form-control form-control-sm"> <br>
					Doctor Phone: <input id="doctorphone" name="doctorphone"
						type="text" class="form-control form-control-sm"> <br>
					Doctor Specialty: <input id="doctorspecialty" name="doctorspecialty"
						type="text" class="form-control form-control-sm"> <br>
					Doctor Experience: <input id="doctorexperience"
						name="doctorexperience" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divDoctorsGrid">
					<%
					Doctor doctorObj = new Doctor();
					out.print(doctorObj.readDoctors());
					%>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>