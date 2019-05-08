<!DOCTYPE html>

<html>

<head>

<title>Add new client</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<body>

<div id="container">

	<h3>Add new client</h3>
	<form action = ClientControllerServlet method="GET"> 
	
		<input type="hidden" name="command" value="ADD" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Name:</label></td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				<tr>
					<td><label>City:</label></td>
					<td><input type="text" name="city" /></td>
				</tr>
				
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
		<a href="ClientControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>