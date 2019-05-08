<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update client</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
Client thisClient = (Client) request.getAttribute("THE_CLIENT");

%>

<body>

<div id="container">

	<h3>Update client</h3>
	<form action = ClientControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= thisClient.getcID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Name:</label></td>
					<td><input type="text" name="name" value="<%= thisClient.getName()%>"/></td>
				</tr>
				
				<tr>
					<td><label>City:</label></td>
					<td><input type="text" name="city" value="<%= thisClient.getCity()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="username" value="<%= thisClient.getUsername()%>"/></td>
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
		<a href="ClientControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>