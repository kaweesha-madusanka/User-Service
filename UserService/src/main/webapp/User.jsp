<%@page import="com.User"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.6.0.min.js"></script> 
<script src="Components/user.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>User Details</h1>
				<form id="formUser" name="formUser" method="post" action="User.jsp">  
					<br>name:  
 	 				<input id="name" name="name" type="text"  class="form-control form-control-sm">
					
					<br> nic:   
  					<input id="nic" name="nic" type="text" class="form-control form-control-sm">   
  					
					
					<br> address:   
  					<input id="address" name="address" type="text"  class="form-control form-control-sm">
					<br> 
					
					<br> mobile:   
  					<input id="mobile" name="mobile" type="text"  class="form-control form-control-sm">
					<br> 
					
					<br> email:   
  					<input id="email" name="email" type="text"  class="form-control form-control-sm">
					<br> 
					
					<br> ebill:   
  					<input id="ebill" name="ebill" type="text"  class="form-control form-control-sm">
					<br> 
					
					<br> created_at:   
  					<input id="created_at" name="created_at" type="text"  class="form-control form-control-sm">
					<br> 
					
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hiduidSave" name="hiduidSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divuserGrid">
					<%
						User innoObj = new User();
						out.print(innoObj.readUser());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>