<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager</title>

<link type="text/css" rel="stylesheet" href="css/design.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouse Manager</h2>
		</div>
	</div>

	<div id="outer">
		<div class="inner">
			<h3>Menu</h3>


			<form action="ProductControllerServlet" method="GET">
				<input type="hidden" name="command" value="ListProducts" /> <input
					type="submit" value="Show All Products" name="btShowProducts" />
			</form>

			<form action="ClientControllerServlet" method="GET">
				<input type="hidden" name="command" value="ListClients" /> <input
					type="submit" value="Show All Clients" name="btShowClients" />
			</form>

			<form action="InvoiceControllerServlet" method="GET">
				<input type="hidden" name="command" value="ListInvoices" /> <input
					type="submit" value="Show All Invoices" name="btShowInvoices" />
			</form>

			<form action="OrderControllerServlet" method="GET">
				<input type="hidden" name="command" value="ListOrders" /> <input
					type="submit" value="Show All Orders" name="btShowOrders" />
			</form>

			<form action="UserControllerServlet" method="GET">
				<input type="hidden" name="command" value="LogOut" /> <input
					type="submit" value="Logout" name="btLogOut" />
			</form>

		</div>

	</div>

</body>



</html>