<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Cart> cart = (ArrayList<Cart>) request.getAttribute("ORDER_LIST");
	float netSum = (float) request.getAttribute("NET");
	float grossSum = (float) request.getAttribute("GROSS");
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
		<div style="float: left; width: 150px">
					<form action="UserControllerServlet" method="GET">
						<input type="hidden" name="command" value="MENU" /> <input
							type="submit" value="Back to menu" name="btmenu" />
					</form>
				</div>
			
		<div style="float: right; width: 150px">
				<% if(function == 6){ %>
					<form action="OrderControllerServlet" method="GET">
						<input type="hidden" name="command" value="ShowMyOrders" /> <input
							type="submit" value="Back to orders" name="btmyorders" />
					</form>
				<%}else { %>
					<form action="OrderControllerServlet" method="GET">
						<input type="hidden" name="command" value="ListOrders" /> <input
							type="submit" value="Back to orders" name="btorders" />
					</form>
				<%} %>
				</div>
				</div>

				


		<table border="1">

			<tr>
				<th>product ID</th>
				<th>Name</th>
				<th>Net price</th>
				<th>Gross price</th>

			</tr>


			<%
				for (int i = 0; i < cart.size(); i++) {
			%>

			<tr>
				<td><%=cart.get(i).getpID()%></td>
				<td><%=cart.get(i).getName()%></td>
				<td><%=cart.get(i).getNET_price()%></td>
				<td><%=cart.get(i).getGROSS_price()%></td>
			</tr>

			<%
				}
			%>
		</table>

		<table>

			<tr>
				<th></th>
				<th></th>
				<th>Net price</th>
				<th>Gross price</th>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><%=netSum%></td>
				<td><%=grossSum%></td>
			</tr>
		</table>
	</div>



</body>



</html>