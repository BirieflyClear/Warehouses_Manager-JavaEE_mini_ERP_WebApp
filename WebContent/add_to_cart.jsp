<!DOCTYPE html>

<html>

<head>

<title>Add to cart</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<body>

	<div id="container">

		<h3>Add to cart</h3>

		<form action="CartControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			<table>
				<tbody>

					<tr>
						<td><label>Quantity:</label></td>
						<td><input type="number" name="quantity" max="3" value="1"/></td>
					</tr>

					<tr>
						<td><label>Add this product to cart?</label></td>
						<td></td>
					</tr>

					<tr>
						<td><input type="submit" value="Yes" /></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</form>
		<table>
			<tbody>
				<tr>
					<td>
						<form action=ProductControllerServlet method="GET">
							<input type="hidden" name="command" value="ListProducts" /> <input
								type="submit" value="No" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>