<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Warehouse> theWarehouses = (ArrayList<Warehouse>) request.getAttribute("WAREHOUSE_LIST");
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
				<input type="button" value="Add warehouse"
					onclick="window.location.href='add_new_warehouse.jsp'; return false;" />


				<form action="UserControllerServlet" method="GET">
					<input type="hidden" name="command" value="MENU" /> <input
						type="submit" value="Back to menu" name="btmenu" />
				</form>
			</div>
		</div>


		<table border="1" class="center">
		<caption>Warehouses</caption>
			<tr>
				<th>ID</th>
				<th>City</th>
				<th>Products' type</th>
				<th>Capacity</th>
				<th>Action</th>
			</tr>


			<%
				for (int i = 0; i < theWarehouses.size(); i++) {
			%>

			<tr>
				<td><%=theWarehouses.get(i).getwID()%></td>
				<td><%=theWarehouses.get(i).getCity()%></td>
				<td><%=theWarehouses.get(i).getProducts_type()%></td>
				<td><%=theWarehouses.get(i).getCapacity()%></td>
				<td><form action=ProductControllerServlet method="GET">
						<input type="hidden" name="command" value="ListThisWarehouse" />
						<input type="hidden" name="id"
							value="<%=theWarehouses.get(i).getwID()%>" /> <input
							type="submit" value="Show products" />
					</form>
					<form action="WarehouseControllerServlet" method="GET">

						<input type="hidden" name="command" value="LOAD" /> <input
							type="hidden" name="id"
							value="<%=theWarehouses.get(i).getwID()%>" /> <input
							type="submit" value="Update" />

					</form>
					<form>

						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id"
							value="<%=theWarehouses.get(i).getwID()%>" /> <input
							type="submit" value="Delete" />

					</form></td>
			</tr>

			<%
				}
			%>
		</table>

	</div>


</body>



</html>