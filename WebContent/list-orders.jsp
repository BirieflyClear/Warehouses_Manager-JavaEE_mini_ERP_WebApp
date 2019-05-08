<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Order> theOrders = (ArrayList<Order>) request.getAttribute("ORDER_LIST");
	int function = (int) request.getAttribute("FUNCTION");
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
				
		<table border="1" class="center">
		<caption>Orders</caption>
			<tr>
				<th>ID</th>
				<th>Client's ID</th>
				<th>Client's name</th>
				<th>Net price</th>
				<th>Gross price</th>
				<th>Client's city</th>
				<th>Warehouse ID</th>
				<th>Status</th>
				<th>Invoice's status</th>
				<th>Action</th>
			</tr>


			<%
				for (int i = 0; i < theOrders.size(); i++) {
			%>

			<tr>
				<td><%=theOrders.get(i).getoID()%></td>
				<td><%=theOrders.get(i).getClient_ID()%></td>
				<td><%=theOrders.get(i).getClient_name()%></td>
				<td><%=theOrders.get(i).getNET_price()%></td>
				<td><%=theOrders.get(i).getGROSS_price()%></td>
				<td><%=theOrders.get(i).getClient_city()%></td>
				<td><%=theOrders.get(i).getWarehouse_ID()%></td>
				<td><%=theOrders.get(i).getStatus()%></td>
				<td><%=theOrders.get(i).getInvoice_status()%></td>
				<td>
					<%
						if (function == 6) {
					%> <%
 	if (theOrders.get(i).getInvoice_status().length() == 9) {
 %>
					<form action=OrderControllerServlet method="GET">
						<input type="hidden" name="command" value="PAY" /> <input
							type="hidden" name="id" value="<%=theOrders.get(i).getoID()%>" />
						<input type="submit" value="Pay" />
					</form> <%
 	}
 		} else if (function == 10) {
 %>
					<form action=OrderControllerServlet method="GET">
						<input type="hidden" name="command" value="ChangeStatus" /> <input
							type="hidden" name="id" value="<%=theOrders.get(i).getoID()%>" />
						<select name="status">
							<option value="pending">Pending</option>
							<option value="preparing">Preparing</option>
							<option value="shipped">Shipped</option>
							<option value="delivered">Delivered</option>
						</select> <input type="submit" value="save" />
					</form> <%
					if(theOrders.get(i).getInvoice_status().length() == 10){	%>
						
						<form action="InvoiceControllerServlet" method="GET">
						<input type="hidden" name="command" value="ADD"/>
						<input type="hidden" name="oID" value="<%=theOrders.get(i).getoID()%>"/>
						<input type="submit" value="make invoice"/>
						</form>
						
		<%			}
 	}
 %>
					<form action=OrderControllerServlet method="GET">
						<input type="hidden" name="command" value="ShowDetails" /> <input
							type="hidden" name="id" value="<%=theOrders.get(i).getoID()%>" />
						<input type="submit" value="Show Details" />
					</form> <%
 	if (function == 10 || function == 5) {
 %>
					<form action="OrderControllerServlet" method="GET">
						<input type="hidden" name="command" value="LOAD" /> <input
							type="hidden" name="id" value="<%=theOrders.get(i).getoID()%>" />
						<input type="submit" value="Update" />
					</form>
					<form>
						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id" value="<%=theOrders.get(i).getoID()%>" />
						<input type="submit" value="Delete" />
					</form> <%
 	}
 %>
				</td>
			</tr>

			<%
				}
			%>
		</table>

	</div>


</body>



</html>