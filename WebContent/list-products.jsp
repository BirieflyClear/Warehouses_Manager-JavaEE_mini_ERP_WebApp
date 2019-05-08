<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>
<link type="text/css" rel="stylesheet" href="css/design.css">

</head>

<%
	//get the users from the request object (sent by servlet)
	ArrayList<Product> theProducts = (ArrayList<Product>) request.getAttribute("PRODUCT_LIST");
	int function = (int) request.getAttribute("FUNCTION");
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
				<%
					if (function == 5) {
				%>
				<input type="button" value="Add product"
					onclick="window.location.href='add_new_product.jsp'; return false;" />

				<%
					}
				%>
			
			
		
				<form action="UserControllerServlet" method="GET">
					<input type="hidden" name="command" value="MENU" /> <input
						type="submit" value="Back to menu" name="btmenu" />
				</form>
			</div>
		</div>

		<table border="1" class="center">
		<caption>Products</caption>

			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Type</th>
				<th>Net price</th>
				<th>Gross price</th>
				<th>Warehouse's ID</th>
				<th>Quantity</th>
				<th>Action</th>
			</tr>


			<%
				for (int i = 0; i < theProducts.size(); i++) {
			%>

			<tr>
				<td><%=theProducts.get(i).getpID()%></td>
				<td><%=theProducts.get(i).getName()%></td>
				<td><%=theProducts.get(i).getType()%></td>
				<td><%=theProducts.get(i).getNET_price()%></td>
				<td><%=theProducts.get(i).getGROSS_price()%></td>
				<td><%=theProducts.get(i).getWarehouse_ID()%></td>
				<td><%=theProducts.get(i).getQuantity()%></td>
				<td>
					<%
						if (function == 6) {
					%>
					<form action=CartControllerServlet method="GET">
						<input type="hidden" name="command" value="AddToCart" /> <input
							type="hidden" name="id" value="<%=theProducts.get(i).getpID()%>" />
						<input type="submit" value="Add to cart" />
					</form> <%
 	}
 %> <%
 	if (function == 5) {
 %>
					<form action="ProductControllerServlet" method="GET">

						<input type="hidden" name="command" value="LOAD" /> <input
							type="hidden" name="id" value="<%=theProducts.get(i).getpID()%>" />
						<input type="submit" value="Update" />

					</form>
					<form>

						<input type="hidden" name="command" value="DELETE" /> <input
							type="hidden" name="id" value="<%=theProducts.get(i).getpID()%>" />
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