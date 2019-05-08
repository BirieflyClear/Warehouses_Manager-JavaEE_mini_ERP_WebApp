<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link type="text/css" rel="stylesheet" href="css/design.css">
</head>


<%
	//get the users from the request object (sent by servlet)
	LoggedUser user = (LoggedUser) request.getAttribute("USER");
%>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouse Manager</h2>
		</div>
	</div>
	
	<div id="container">
		
		
		<form action="UserControllerServlet" method="GET">
			<input type="hidden" name="command" value="MENU" />
			<table>
				<tbody>
					<tr>
						Welcome <%= user.getUsername() %>
					</tr>
					<tr>
					</tr>
					
					
				</tbody>
			</table>
			
			<div><input type="submit" value="OK" name="submitButton" /></div>
		</form>
	</div>

</body>



</html>