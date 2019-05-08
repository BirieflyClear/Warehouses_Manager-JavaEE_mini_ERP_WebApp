<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<ProductType> types = (ArrayList<ProductType>) request.getAttribute("TYPES_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouses</h2>
		</div>
	</div>

	<div id="outer">
		<div class="inner">


			<form action="UserControllerServlet" method="GET">
				<input type="hidden" name="command" value="MENU" /> <input
					type="submit" value="Back to menu" name="btmenu" />
			</form>
		</div>
	</div>

	<table border="1" class="center">
		<caption>Product's types</caption>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Action</th>
		</tr>


		<%
			for (int i = 0; i < types.size(); i++) {
		%>

		<tr>
			<td><%=types.get(i).gettID()%></td>
			<td><%=types.get(i).getTypeName()%></td>
			<td><form action="ProductTypeControllerServlet" method="GET">

					<input type="hidden" name="command" value="LOAD" /> <input
						type="hidden" name="id" value="<%=types.get(i).gettID()%>" /> <input
						type="submit" value="Update" />

				</form></td>


		</tr>

		<%
			}
		%>
	</table>





</body>



</html>