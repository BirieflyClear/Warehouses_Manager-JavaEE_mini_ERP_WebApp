<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Client> theClients = (ArrayList<Client>) request.getAttribute("CLIENT_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouses</h2>
		</div>
	</div>

	<div id="container">

		<div id="outer">
			<div class="inner">
				<form action="UserControllerServlet" method="GET">
					<input type="hidden" name="command" value="MENU" /> <input
						type="submit" value="Back to menu" name="btmenu" />
				</form>
			</div>
		</div>

		<table border="1" class="center">

			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>City</th>
				<th>Username</th>
				<th>Action</th>
			</tr>


			<%
				for (int i = 0; i < theClients.size(); i++) {
			%>

			<tr>
				<td><%=theClients.get(i).getcID()%></td>
				<td><%=theClients.get(i).getName()%></td>
				<td><%=theClients.get(i).getCity()%></td>
				<td><%=theClients.get(i).getUsername()%></td>
				<td><form action="ClientControllerServlet" method="GET">

						<input type="hidden" name="command" value="LOAD" /> <input
							type="hidden" name="id" value="<%=theClients.get(i).getcID()%>" />
						<input type="submit" value="Update" />

					</form>
					<form>

						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id" value="<%=theClients.get(i).getcID()%>" />
						<input type="submit" value="Delete" />

					</form></td>
			</tr>

			<%
				}
			%>
		</table>

	</div>



</body>



</html>