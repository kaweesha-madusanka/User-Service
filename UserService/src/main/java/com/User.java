package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



//data base connection

public class User {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ceb_api", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//insert

	public String insertUser(String uid, String name, String nic, String address, String mobile, String email, String ebill, String created_at) { 
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " INSERT into users (`uid`,`name`,`nic`,`address`,`mobile`,`email`,`ebill`,`created_at`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, uid);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, mobile);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, ebill);
			preparedStmt.setString(8, created_at);
	 
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newUser = readUser(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the User.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	    } 
	}
	
	//read
	
	public String readUser()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1'><tr>"
					+ "<th> UID</th>"
					+ "<th> Name</th>"
					+ "<th> NIC</th>" 
					+ "<th> Address</th>"
					+ "<th> Mobile</th>"
					+ "<th> Email</th>"
					+ "<th> Ebill</th>"
					+ "<th>CreatedAt</th></tr>";
			
			String query = "select * from User"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String uid = rs.getString("uid");
				String name = rs.getString("name"); 
				String nic = rs.getString("nic"); 
				String address = rs.getString("address"); 
				String mobile = rs.getString("mobile"); 
				String email = rs.getString("email"); 
				String ebill = rs.getString("ebill"); 
				String created_at = rs.getString("created_at"); 
		 
			
			
	 
				// Add into the html table 
				
				output += "<tr><td><input id=\'hiduidUpdate\' name=\'hiduidUpdate\' type=\'hidden\' value=\'" + uid + "'>"
				            + name + "</td>"; 
				output += "<td>" + nic + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + mobile + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + ebill + "</td>";
				output += "<td>" + created_at + "</td>";
				
			  
 
			// buttons     
			output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iid='" + uid + "'>" + "</td></tr>"; 
		 		 
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the User.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//update
	
	public String updateUser(String uid, String name, String nic, String address, String mobile, String email, String ebill, String created_at) { 
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = " UPDATE users SET name = ? , nic = ? , address = ? , mobile = ?, email = ?, ebill = ?, created_at = ? WHERE uid = ? ";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, mobile);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, ebill);
			preparedStmt.setString(7, created_at);
			preparedStmt.setString(8, uid);
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	    } 
	}
	
	//delete
	
	public String deleteUser(String uid)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "DELETE from users WHERE uid=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(uid)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the User.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
