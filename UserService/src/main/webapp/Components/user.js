$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateUserForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hiduidSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "UserService",  
			type : type,  
			data : $("#formUser").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onUserSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onUserSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divUserGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidInnovatorIDSave").val("");  
	$("#formInnovator")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hiduidSave").val($(this).closest("tr").find('#hiduidUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#nic").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#mobile").val($(this).closest("tr").find('td:eq(3)').text());  
	$("#email").val($(this).closest("tr").find('td:eq(4)').text());  
	$("#ebill").val($(this).closest("tr").find('td:eq(5)').text());  
	$("#created_at").val($(this).closest("tr").find('td:eq(6)').text());      
}); 


//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "UserService",   
		type : "DELETE",   
		data : "uid=" + $(this).data("iid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onUserDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onUserDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divUserGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateUserForm() 
{  
	// NAME  
	if ($("#name").val().trim() == "")  
	{   
		return "Insert holder_name.";  
	} 

	// NIC------------------------  
	if ($("#nic").val().trim() == "")  
	{   
		return "Insert nic.";  
	} 
	
	

	//address-------------------------------
	if ($("#address").val().trim() == "")  
	{   
		return "Insert address.";  
	} 
	
	
	//mobile-------------------------------
	if ($("#mobile").val().trim() == "")  
	{   
		return "Insert mobile.";  
	} 
	
	//email-------------------------------
	if ($("#email").val().trim() == "")  
	{   
		return "Insert email.";  
	} 
	
	//ebill-------------------------------
	if ($("#ebill").val().trim() == "")  
	{   
		return "Insert ebill.";  
	} 
	
	//created_at-------------------------------
	if ($("#created_at").val().trim() == "")  
	{   
		return "Insert created_at.";  
	} 
	
	return true; 
}