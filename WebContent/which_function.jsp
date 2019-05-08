<!DOCTYPE html>

<html>

<head>

<title>Add new user</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<body>

	<div id="container">


		<form action=UserControllerServlet method="GET">
			<input type="hidden" name="command" value="FUNCTION" />

			<table>
				<tr>
					<td>Choose function</td>
					<td><select name=function>
							<option value="client">Client</option>
							<option value=accountant>Accountant</option>
							<option value=admin>Admin</option>
					</select></td>
				</tr>
			</table>
			<input type=submit value=Next class=save />



		</form>



		<div style="clear: both;"></div>
		<p>
			<a href="UserControllerServlet">Back to the list</a>
		</p>

	</div>

</body>
</html>