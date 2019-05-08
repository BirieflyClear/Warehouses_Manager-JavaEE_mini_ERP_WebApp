<%@ page import="java.util.*, pl.kti.dbservlet.*" %>
<!DOCTYPE html>

<html>

<head>

<title>Update invoice</title>

<link type="text/css" rel="stylesheet" href="css/add-style.css">

</head>

<% 
ProductType type = (ProductType) request.getAttribute("THIS");

%>

<body>

<div id="container">

	<h3>Update invoice</h3>
	<form action = ProductTypeControllerServlet method="GET" > 
	
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="id" value="<%= type.gettID()%>" />
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Type Name</label></td>
					<td><input type="text" name="type_name" value="<%= type.getTypeName()%>"/></td>
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
		<a href="ProductTypeControllerServlet">Back to the list</a>
		</p>
	
</div>

</body>
</html>