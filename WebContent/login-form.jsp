<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Login</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouse Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Login</h3>

		<form action="UserControllerServlet" method="GET">
			<input type="hidden" name="command" value="LOGIN" />
			<table>
				<tbody>
					<tr>
						<td><label>Username:</label> <input type="text"
							name="usernameValue" /></td>
					</tr>
					<tr>
						<td><label>Password:</label> <input type="password"
							name="passwordValue" /></td>
					</tr>
					<tr>
						<td>
						<input type="submit" value="Login" name="loginButton" />
						</td>
					</tr>

				</tbody>
			</table>

		</form>
	</div>

</body>



</html>