<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
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
	
	<div id="container">
		
		
			<table>
				<tbody>
					<tr>
						<td>
							<h3>YOUR CART IS EMPTY</h3>
						</td>
					</tr>
					<tr>
						<td>
						<label></label>
						</td>
					</tr>
					
					<tr>
						<td>
							<form action="CartControllerServlet" method="GET">
								<input type="hidden" name="command" value="ShowCart" />
									<input type="submit" value="OK" name="btok" />
							</form>
						</td>
					</tr>
					
				</tbody>
			</table>
			
		
	</div>

</body>



</html>