<!DOCTYPE html>

<html>

<head>

<title>Add new invoice</title>
<link type="text/css" rel="stylesheet" href="css/add-style.css">


</head>

<body>

<div id="container">

	<h3>Add new invoice</h3>
	<form action = InvoiceControllerServlet method="GET">
	
		<input type="hidden" name="command" value="ADD" />
		
		<table>
			<tbody>
			
				
				
				<tr>
					<td><label>Order's ID:</label></td>
					<td><input type="number" name="order_ID" /></td>
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