<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update product</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
Product thisProduct = (Product) request.getAttribute("THE_PRODUCT");

%>

<body>

<div id="container">

	<h3>Update product</h3>
	<form action = ProductControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= thisProduct.getpID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Product's name</label></td>
					<td><input type="text" name="name" value="<%= thisProduct.getName()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Product's type:</label></td>
					<td><input type="text" name="type" value="<%= thisProduct.getType()%>"/></td>
				</tr>
				
				<tr>
					<td><label>NET price:</label></td>
					<td><input type="number" name="NET_price" value="<%= thisProduct.getNET_price()%>"/></td>
				</tr>
				
				<tr>
					<td><label>GROSS price:</label></td>
					<td><input type="number" name="GROSS_price" value="<%= thisProduct.getGROSS_price()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Warehouse's ID:</label></td>
					<td><input type="number" name="warehouse_ID" value="<%= thisProduct.getWarehouse_ID()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Quantity:</label></td>
				<td><input type="number" name="quantity" value="<%= thisProduct.getQuantity()%>"/></td>
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
		<a href="ProductControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>