<%@ page import="java.util.*, pl.kti.dbservlet.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Warehouse Manager App</title>

<link type="text/css" rel="stylesheet" href="css/design.css">
</head>

<%
	//get the users from the request object (sent by servlet)
	String msg = (String) request.getAttribute("MSG");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Biesiabysz Warehouses</h2>
		</div>
	</div>

	<div id="container">




		<P><%=msg%></P>



	</div>
	<div id="button">
		<input type="button" value="OK"
			onclick="window.location.href='add_new_invoice.jsp'; return false;" />
	</div>


</body>



</html>