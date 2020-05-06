/**
 * 
 */

$(document).ready(function() 
{
	
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidDoctorIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "DoctorsAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSaveComplete(response.responseText, status);
		}
	});
});

function onDoctorSaveComplete(response, status) {
	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divDoctorsGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {

		$("#alertError").text("Error while saving.");
		$("#alertError").show();

	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidDoctorIDSave").val("");
	$("#formDoctor")[0].reset();
}

// UPDATE==========================================
$(document)
		.on(
				"click",
				".btnUpdate",
				function(event) {
					$("#hidDoctorIDSave")
							.val(
									$(this).closest("tr").find(
											'#hidDoctorIDUpdate').val());
					$("#doctorname").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#doctorphone").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#doctorspecialty").val(
							$(this).closest("tr").find('td:eq(3)').text());
					$("#doctorexperience").val(
							$(this).closest("tr").find('td:eq(4)').text());

				});

// REMOVE=============================================================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "DoctorsAPI",
		type : "DELETE",
		data : "doctorid=" + $(this).data("doctorid"),
		dataType : "text",
		complete : function(response, status) {
			onDoctorDeleteComplete(response.responseText, status);
		}
	});
});

function onDoctorDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENTMODEL=========================================================================
function validateDoctorForm() {
	// cardHolderName
	if ($("#doctorname").val().trim() == "") {
		return "Insert Doctor Name.";
	}	
	// cardHolderName
	if ($("#doctorphone").val().trim() == "") {
		return "Insert Doctor Phone.";
	}	
	// is numerical value
	var tmpPhone = $("#doctorphone").val().trim();
	if (!$.isNumeric(tmpPhone)) {
		return "Insert a numerical value for Phone.";
	}	
	// cardHolderName
	if ($("#doctorspecialty").val().trim() == "") {
		return "Insert Doctor Specialty.";
	}
	// cardHolderName
	if ($("#doctorexperience").val().trim() == "") {
		return "Insert Doctor Experience.";
	}
	

		return true;
}
