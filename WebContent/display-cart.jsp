<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>

<link type="text/css" rel="stylesheet" href="css/design.css">
</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Cart> cart = (ArrayList<Cart>) request.getAttribute("CART_LIST");
	float netSum = (float) request.getAttribute("NET");
	float grossSum = (float) request.getAttribute("GROSS");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouses</h2>
		</div>
	</div>

	<div id="container">

		<div style="width: 500px">
		<div style="float: left; width: 150px">
					<form action="UserControllerServlet" method="GET">
						<input type="hidden" name="command" value="MENU" /> <input
							type="submit" value="Back to menu" name="btmenu" />
					</form>
				</div>
				<div style="float: left; width: 225px">
					<form action="ProductControllerServlet" method="GET">
						<input type="hidden" name="command" value="ListProducts" /> <input
							type="submit" value="Back to products" name="btproducts" />
					</form>
				</div>
				<div style="width: 400px">
		<div style="float: left; width: 150px">
					<form action="CartControllerServlet" method="GET">
						<input type="hidden" name="command" value="CLEAR" /> <input
							type="submit" value="Delete this cart" name="btclear" />
					</form>
				</div>
				<div style="float: left; width: 225px">
					<form action="CartControllerServlet" method="GET">
						<input type="hidden" name="command" value="PAY" /> <input
							type="submit" value="Place an order" name="btpay" />
					</form>
				</div>
				</div>


		<table border="1">

			<tr>
				<th>ID</th>
				<th>product ID</th>
				<th>Name</th>
				<th>Net price</th>
				<th>Gross price</th>
				<th>Action</th>

			</tr>


			<%
				for (int i = 0; i < cart.size(); i++) {
			%>

			<tr>
				<td><%=cart.get(i).getID()%></td>
				<td><%=cart.get(i).getpID()%></td>
				<td><%=cart.get(i).getName()%></td>
				<td><%=cart.get(i).getNET_price()%></td>
				<td><%=cart.get(i).getGROSS_price()%></td>
				<td>
					<form action=CartControllerServlet method="GET">
						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id" value="<%=cart.get(i).getID()%>" /> <input
							type="submit" value="Delete from cart" />
					</form>

				</td>
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