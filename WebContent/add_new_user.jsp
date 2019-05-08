<!DOCTYPE html>

<html>

<head>

<title>Add new user</title>
<link type="text/css" rel="stylesheet" href="css/add-style.css">


</head>
<% String f = (String) request.getAttribute("theFunction"); %>

<body>

<div id="container">

	<h3>Add new user</h3>
	<form action = UserControllerServlet method="GET"> 
	
		<input type="hidden" name="command" value="ADD" />
		<input type="hidden" name="function" value="<%=f %>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="username" /></td>
				</tr>
				
				<tr>
					<td><label>Password:</label></td>
					<td><input type="text" name="password" /></td>
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