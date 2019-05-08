<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update user</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
User thisUser = (User) request.getAttribute("THE_USER");

%>

<body>

<div id="container">

	<h3>Update user</h3>
	<form action = UserControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= thisUser.getuID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="username" value="<%= thisUser.getUsername()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Password:</label></td>
					<td><input type="text" name="password" value="<%= thisUser.getPassword()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Function:</label></td>
					<td><input type="text" name="function" value="<%= thisUser.getFunction()%>"/></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>

			</tbody>
		</table>
	
	</form> 
	
	<div style="clear: both;"></div>
		<p>
		<a href="UserControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>