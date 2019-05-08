<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">


</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<User> theUsers = (ArrayList<User>) request.getAttribute("USER_LIST");
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
				<input type="button" value="Add user"
					onclick="window.location.href='which_function.jsp'; return false;" />


				<form action="UserControllerServlet" method="GET">
					<input type="hidden" name="command" value="MENU" /> <input
						type="submit" value="Back to menu" name="btmenu" />
				</form>
			</div>
		</div>


		<table border="1" class="center">
			<caption>Users</caption>
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Password</th>
				<th>Function</th>
				<th>Action</th>
			</tr>


			<%
				for (int i = 0; i < theUsers.size(); i++) {
			%>

			<tr>
				<td><%=theUsers.get(i).getuID()%></td>
				<td><%=theUsers.get(i).getUsername()%></td>
				<td><%=theUsers.get(i).getPassword()%></td>
				<td><%=theUsers.get(i).getFunction()%></td>
				<td><form action="UserControllerServlet" method="GET">

						<input type="hidden" name="command" value="LOAD" /> <input
							type="hidden" name="id" value="<%=theUsers.get(i).getuID()%>" />
						<input type="submit" value="Update" />

					</form>
					<form>

						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id" value="<%=theUsers.get(i).getuID()%>" />
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