<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update Warehouse</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
Warehouse thisWarehouse = (Warehouse) request.getAttribute("THE_WAREHOUSE");

%>

<body>

<div id="container">

	<h3>Update warehouse</h3>
	<form action = WarehouseControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= thisWarehouse.getwID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>City:</label></td>
					<td><input type="text" name="city" value="<%= thisWarehouse.getCity()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Product's type:</label></td>
					<td><input type="text" name="products_type" value="<%= thisWarehouse.getProducts_type()%>"/></td>
				</tr>
				
				<tr>
					<td><label>Capacity:</label></td>
					<td><input type="number" name="capacity" value="<%= thisWarehouse.getCapacity()%>"/></td>
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
		<a href="WarehouseControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>