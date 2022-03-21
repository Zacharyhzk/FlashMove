<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="database.DriverDetails"%>
<%@ page import="database.DriverDBAO"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/driver.css">
<script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.0.0.js"></script>
<title>Driver Page</title>
</head>
<body>
<%
DriverDBAO driverDBAO = new DriverDBAO();
List driverDetails = null;
driverDetails = driverDBAO.getDrivers();
%>
	<div class="dashboard">
		<div class="box bg">
			<h2 class="box_title">FAVORITE DRIVERS</h2>
			<table class="table table-bordered">
				<tr>
					<td>Name</td>
					<td>Vehicle</td>
					<td>Avg. Rating</td>
					<!-- <td>Option</td> -->
				</tr>
				<%
				List<DriverDetails> list = (List<DriverDetails>) driverDetails;
				for (DriverDetails t : list) {
				%>
				<tr>
					<td><%=t.getDriverName()%></td>
					<td><%=t.getVehicle()%></td>
					<td><%=t.getRating()%></td>
				<!-- 	<td><a class="btn btn-success btn-sm">Print</a></td> -->

				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>