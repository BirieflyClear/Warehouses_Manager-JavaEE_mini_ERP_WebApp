<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update invoice</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
Invoice thisInvoice = (Invoice) request.getAttribute("THE_INVOICE");

%>

<body>

<div id="container">

	<h3>Update invoice</h3>
	<form action = InvoiceControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= thisInvoice.getiID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Client's ID:</label></td>
					<td><input type="number" name="client_ID" value="<%= thisInvoice.getClientID()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Client's name:</label></td>
					<td><input type="text" name="client_name" value="<%= thisInvoice.getClient_name()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Order's ID:</label></td>
					<td><input type="number" name="order_ID" value="<%= thisInvoice.getOrder_ID()%>"/></td>
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
		<a href="InvoiceControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>