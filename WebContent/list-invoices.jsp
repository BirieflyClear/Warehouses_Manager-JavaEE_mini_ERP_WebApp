<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Invoice> theInvoices = (ArrayList<Invoice>) request.getAttribute("INVOICE_LIST");
	int function = (int) request.getAttribute("FUNCTION");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouses</h2>
		</div>
	</div>

	<div id="container">

		<div style="width: 400px">
		<div style="float: right; width: 150px">
				<%
					if (function == 10) {
				%>
				
			<!--  	<input type="button" value="Add invoice"
					onclick="window.location.href='add_new_invoice.jsp'; return false;" />
				-->	
				<%
					}
				%>
				</div>
				
		<div style="float: left; width: 150px">
					<form action="UserControllerServlet" method="GET">
						<input type="hidden" name="command" value="MENU" /> <input
							type="submit" value="Back to menu" name="btmenu" />
					</form>
				</div>
				</div>


		<table border="1" class="center">
		<caption>Invoices</caption>
			<tr>
				<th>ID</th>
				<th>Client's ID</th>
				<th>Client's name</th>
				<th>Order's ID</th>
				<th>NET VALUE</th>
				<th>GROSS VALUE</th>
				<th>Action</th>

			</tr>


			<%
				for (int i = 0; i < theInvoices.size(); i++) {
			%>

			<tr>
				<td><%=theInvoices.get(i).getiID()%></td>
				<td><%=theInvoices.get(i).getClientID()%></td>
				<td><%=theInvoices.get(i).getClient_name()%></td>
				<td><%=theInvoices.get(i).getOrder_ID()%></td>
				<td><%=theInvoices.get(i).getNetValue()%></td>
				<td><%=theInvoices.get(i).getGrossValue()%></td>
				<td>
					<%
						if (function == 10 || function == 5) {
					%>
					<form action="InvoiceControllerServlet" method="GET">
						<input type="hidden" name="command" value="LOAD" /> <input
							type="hidden" name="id" value="<%=theInvoices.get(i).getiID()%>" />
						<input type="submit" value="Update" />
					</form>
					<form>
						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id" value="<%=theInvoices.get(i).getiID()%>" />
						<input type="submit" value="Delete" />
					</form> <%
 	} else {
 %>
					
						<label>No Action</label>
					 <%
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