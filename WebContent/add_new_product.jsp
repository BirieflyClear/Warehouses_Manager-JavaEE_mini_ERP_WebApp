<!DOCTYPE html>

<html>

<head>

<title>Add new product</title>
<link type="text/css" rel="stylesheet" href="css/add-style.css">


</head>

<body>

<div id="container">

	<h3>Add new product</h3>
	<form action = ProductControllerServlet method="GET">
	
		<input type="hidden" name="command" value="ADD" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Product's name:</label></td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				<tr>
					<td><label>Product's type:</label></td>
					<td><input type="text" name="type" /></td>
				</tr>
				
				<tr>
					<td><label>NET price:</label></td>
					<td><input type="number" name="NET_price" /></td>
				</tr>
				
				<tr>
					<td><label>GROSS price:</label></td>
					<td><input type="number" name="GROSS_price" /></td>
				</tr>
				
				<tr>
					<td><label>Warehouse's ID:</label></td>
					<td><input type="number" name="warehouse_ID" /></td>
				</tr>
				
				<tr>
					<td><label>Quantity:</label></td>
					<td><input type="number" name="quantity" /></td>
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