<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link type="text/css" rel="stylesheet" href="css/design.css">
</head>

<%
	//get the users from the request object (sent by servlet)
	String user = (String) request.getAttribute("USER");
%>


<body>
	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouse Manager</h2>
		</div>
	</div>
	
	<div id="container">
		
		
		<form action="UserControllerServlet" method="GET">
			<input type="hidden" name="command" value="LOGIN" />
			<table>
				<tbody>
					<tr>
						You are trying to log in as <%= user %>
					</tr>
					<tr>
						Username or password is wrong
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					
				</tbody>
			</table>
			
		</form>
		
		<div id="linkBack">
		<p>
			<a href="login-form.jsp">Back to login</a>
		</p>
		</div>
		
	</div>

</body>



</html>