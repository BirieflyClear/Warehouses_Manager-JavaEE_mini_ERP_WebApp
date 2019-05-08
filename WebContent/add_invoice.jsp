<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Order> theOrders = (ArrayList<Order>) request.getAttribute("ORDERS");
	int i = 0;
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouses</h2>
		</div>
	</div>

	<div id="container">


		<form action="UserControllerServlet" method="GET">
			<input type="hidden" name="command" value="MENU" /> <input
				type="submit" value="Back to menu" name="btmenu" />
		</form>


		<form action="InvoiceControllerServlet" method="GET">
		<table border="1">

			<tr>
				<th>Invoice</th>
				<th>ID</th>
				<th>Client's ID</th>
				<th>Client's name</th>
				<th>Net price</th>
				<th>Gross price</th>
				<th>Client's city</th>
				<th>Status</th>
			</tr>

		
			<%
				for (i = 0; i < theOrders.size(); i++) {
			%>

			<tr>
			<td><input type="checkbox" name="checkbox<%=i %>" value="<%=theOrders.get(i).getoID()%>"></td>
				<td><%=theOrders.get(i).getoID()%></td>
				<td><%=theOrders.get(i).getClient_ID()%></td>
				<td><%=theOrders.get(i).getClient_name()%></td>
				<td><%=theOrders.get(i).getNET_price()%></td>
				<td><%=theOrders.get(i).getGROSS_price()%></td>
				<td><%=theOrders.get(i).getClient_city()%></td>
				<td><%=theOrders.get(i).getStatus()%></td>
			</tr>
			
			<%
				}
			%>
		</table>
			<input type="hidden" name="command" value="ADD" />
			<input type="hidden" name="checkboxes" value="<%=i %>" /> <input
				type="submit" value="SAVE" name="btsave" />
		</form>
	</div>


</body>



</html>