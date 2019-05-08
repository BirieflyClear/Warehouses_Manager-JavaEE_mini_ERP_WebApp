<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update order</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
Order thisOrder = (Order) request.getAttribute("THE_ORDER");

%>

<body>

<div id="container">

	<h3>Update order</h3>
	<form action = OrderControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= thisOrder.getoID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Client's ID:</label></td>
					<td><input type="number" name="client_ID" value="<%= thisOrder.getClient_ID()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Client's name:</label></td>
					<td><input type="text" name="client_name" value="<%= thisOrder.getClient_name()%>"/></td>
				</tr>
				
				<tr>
					<td><label>NET price:</label></td>
					<td><input type="number" name="NET_price" value="<%= thisOrder.getNET_price()%>"/></td>
				</tr>
				
				<tr>
					<td><label>GROSS price:</label></td>
					<td><input type="number" name="GROSS_price" value="<%= thisOrder.getGROSS_price()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Client's city:</label></td>
					<td><input type="text" name="client_city" value="<%= thisOrder.getClient_city()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Warehouse's ID:</label></td>
					<td><input type="number" name="warehouse_ID" value="<%= thisOrder.getWarehouse_ID()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Status:</label></td>
					<td><input type="text" name="status" value="<%= thisOrder.getStatus()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Invoice's status:</label></td>
					<td><input type="text" name="invoice_status" value="<%= thisOrder.getInvoice_status()%>"/></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>

			</tbody>
		</table>
	
	</form> 
	
	<div style="clear: both;"></div>
		<p>
		<a href="OrderControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>