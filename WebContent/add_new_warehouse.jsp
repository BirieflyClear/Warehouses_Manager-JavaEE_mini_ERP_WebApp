<!DOCTYPE html>

<html>

<head>

<title>Add new warehouse</title>
<link type="text/css" rel="stylesheet" href="css/add-style.css">


</head>

<body>

<div id="container">

	<h3>Add new warehouse</h3>
	<form action = WarehouseControllerServlet method="GET">
	
		<input type="hidden" name="command" value="ADD" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>City:</label></td>
					<td><input type="text" name="city" /></td>
				</tr>
				
				<tr>
					<td><label>Products type:</label></td>
					<td><input type="text" name="products_type" /></td>
				</tr>
				
				<tr>
					<td><label>Capacity:</label></td>
					<td><input type="number" name="capacity" /></td>
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