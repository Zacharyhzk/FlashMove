<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,java.sql.*"%>
<%@ page import="database.RecordDetails"%>
<%@ page import="database.RecordDBAO"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/driver.css">
<script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.0.0.js"></script>
<title>Record Page</title>
</head>
<body>
	<%
	String sessionUserName = null;
	sessionUserName = (String) request.getSession().getAttribute("userName");
	RecordDBAO recordDBAO = new RecordDBAO();
	List recordDetails = null;
	/* recordDetails = recordDBAO.getRecords(); */
	recordDetails = recordDBAO.getRecordsByUserName(sessionUserName);

	RecordDetails getRecordDetails = new RecordDetails();
	int recordId;
	recordId = getRecordDetails.getId();
	%>
	<form action="RecordsServlet" method="GET">
		<div class="dashboard">
			<div class="box bg">
				<h2 class="box_title">Records</h2>
				<table class="table table-bordered">
					<tr>
						<td>Status</td>
						<td>Deliver Date</td>
						<td>Route</td>
						<td>Driver</td>
						<td>Type</td>
						<td>Price</td>
						<td>Option</td>
					</tr>
					<%
					List<RecordDetails> list = (List<RecordDetails>) recordDetails;
					for (RecordDetails t : list) {
					%>
					<tr>
						<td><%=t.getStatusName()%></td>
						<td><%=t.getDelivery_date()%></td>
						<td><%=t.getRoute()%></td>
						<td><%=t.getDriver()%></td>
						<td><%=t.getTypeName()%></td>
						<td><%=t.getPrice()%></td>

						<%
						if (t.getStatusName() == "Processing") {
						%>
						<td>
							<input class="btn btn-success btn-sm" onclick="disp_confirm()"
							id="cancelBtn" name="cancelBtn" 
							value=<%=t.getId()%> hidden></input>
							<input class="btn btn-success btn-sm" onclick="disp_confirm()"
							type="submit"
							value="Cancel"></input>
						</td>
						<%
						} else {
						%>
						<!-- 						<td><a class="btn btn-success btn-sm">Print</a></td> -->
						<%
						}
						%>
					</tr>
					<%
					}
					%>
				</table>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//cancel popup
		function disp_confirm() {
			/* 	console.log(a)
				debugger */
			var r = confirm("Comfirm cancel?")
/* 			document.getElementByName("cancelBtn").value = document.getElementByName("cancelBtn").id; */
		/* 	console.log(document.getElementByName("cancelBtn").value);
			console.log(document.getElementByName("cancelBtn").id); */
			if (r == true) {
				//document.getElementByName("cancelBtn").value = document.getElementByName("cancelBtn").id;
				//debugger
				//var recordId = new Java.type("int")
			
		/* 	console.log(recordId); */
			<%-- 
				//console.log(recordId);
	<%recordDBAO.cancelRecord(recordId, 2);%> --%>
		//System.out.print("cancel popup",a); 
				/* document.write("cancel1") */
			} else {
				/* document.write("cancel2") */
			}
		}
	</script>
</body>
</html>