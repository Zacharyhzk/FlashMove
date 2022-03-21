<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="database.TransDetail"%>
<%@ page import="database.RecordDetails"%>
<%@ page import="database.RecordDBAO"%>
<%@ page import="database.DriverDetails"%>
<%@ page import="database.DriverDBAO"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/order.css">
<script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.0.0.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="js/jquery.min.js"></script>
<script src=https://code.jquery.com/jquery-1.12.4.js></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<title>Order Page</title>
</head>
<%
DriverDBAO driverDBAO = new DriverDBAO();
List driverDetails = null;
driverDetails = driverDBAO.getDrivers();
%>
<body>

	<div id="header">
		<div id="selected_type">
			<img src="images/car3.png">
			<p id="selected_type_name">Transportation Type: MPV</p>
			<p id="selected_price">Starting Price: 100</p>
			<p id="selected_volume">Volume: 10*10*10 cm</p>
		</div>
		<div id="option_type">
			<%
			List<TransDetail> list = (List<TransDetail>) request.getAttribute("list");
			%>
			<%
			if (null != list) {
			%>
			<ul>
				<%
				for (TransDetail t : list) {
				%>
				<li class="car_type" price=<%=t.getPrice()%>
					type=<%=t.getTypeName()%> volume=<%=t.getVolume()%>><img
					src=<%=t.getImage()%>></li>
				<%
				}
				%>
			</ul>
			<%
			} else {
			%>
			<ul>
				<li><img src="images/car1.jpg"></li>
				<li><img src="images/car2.jpg"></li>
				<li><img src="images/car3.jpg"></li>
			</ul>
			<%
			}
			%>
		</div>
	</div>

	<div id="content">
		<form action="OrderStore" method="GET" style="text-align: center"
			onsubmit="return checkip()">
			<div style="height: 10%">
				<%-- <span style="text-align:right;width:5%;margin-left:20%">User ID  : </span> --%>
				<input type="text" name="pickup" id="pickup" size="20" class="input"
					placeholder="PICK UP LOCATION"> <input id="TranportPrice"
					type="text" name="price" hidden> <input id="TranportType"
					type="text" name="type" hidden>
			</div>
			<div style="height: 10%">
				<%-- <span style="text-align:right;width:5%;margin-left:17%">Password : </span> --%>
				<input type="text" name="dropoff" id="dropoff" size="20"
					class="input" placeholder="DROP OFF LOCATION">
			</div>
			<div style="height: 10%">
				<input type="text" name="pickupDate" id="datepicker" size="20"
					class="input" placeholder="PICK UP DATE">
			</div>

			<div style="height: 10% ; margin-top: 2%">
				<label> <input id="hide" name="paymentMethod" type="radio"
					value="Cash"
					style="height: 35px; width: 35px; vertical-align: middle;" checked/> Cash
				</label> <label> <input id="show" name="paymentMethod" type="radio"
					value="Credit Card"
					style="height: 35px; width: 35px; vertical-align: middle;" />
					Credit Card
				</label>
			</div>

			<div style="height: 10%">
				<input type="text" name="CardNum" id="CardNum" size="14"
					class="input" placeholder="Card No:" > 
				<!-- <form action="OrderStore" method="GET" style="text-align:center">
			<input type="radio" name="payment" value="Visa" id="visa" />Visa &nbsp;
            <input type="radio" name="payment" value="Master Card" />Master Card &nbsp;
            <input type="radio" name="payment" value="American Express" />American Express &nbsp;
            <input type="radio" name="payment" value="Discover" />Discover <br /><br />
            <label>Card Number:</label>
            <input type="text" name="cardNumber" id="cardNum" size="30" value="" onblur="ValidateCreditCardNumber()" />
        
		</form> -->
					<input type="text"
					name="CardHolder" id="CardHolder" size="3" class="input"
					placeholder="Holder Name:"> <input type="text"
					name="IssueDate" id="IssueDate" size="3" class="input"
					placeholder="Issue Date:"> <input type="text" name="CCV"
					id="CCV" size="3" class="input" placeholder="CCV:"> 
			</div>

			<div style="margin-top: 2%">
				<span>Please Choose Driver</span> <select name="driver">
					<%
					List<DriverDetails> driverList = (List<DriverDetails>) driverDetails;
					for (DriverDetails t : driverList) {
						/* String type = document.getElementById('type').value */
						/* 	if (t.getVehicle() == "VAN") { */
					%>
					<option><%=t.getDriverName()%></option>
					<%-- <option><%=t.getVehicle()%></option> --%>
					<!-- <option>$(this).attr("type")</option> -->
					<!-- 	<option value="Default">Default</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
					<option value="E">E</option> -->
					<%
					/* } */
					}
					%>
				</select>
			</div>
			<div>
				<input type="submit" value="Confirm" class="submit" id="bt">
			</div>
		</form>
	</div>

	<script type="text/javascript">
		/*
		window.onload = function(){
		    $.ajax({
		    	type:"get",
		    	url:"/FlashMove/transportation",
		    	success:function(data) {
		    		$.each(data, function(index, ele) {
		    			$("#types").append("<li>" + ele.price + "</li>");
		    		})
		    	}
		    })
		}
		 */

		$(".car_type").click(function() {
			$(this).find('img').each(function(index, img) {
				var path = $(img).attr('src');
				console.log(path);
				$("#selected_type img").attr('src', path);
			})
			console.log($(this).attr('type'));
			console.log($(this).attr('price'));
			console.log($(this).attr('volume'));
			var type = $(this).attr("type");
			var price = $(this).attr("price");
			var volume = $(this).attr("volume");
			$("#selected_type_name").text("Transportation Type: " + type);
			$("#selected_price").text("Starting Price: " + price);
			$("#selected_volume").text("Volume: " + volume + " cm");
			$("#TranportPrice").val(price);
			$("#TranportType").val(type);
		});

		$(document).ready(function() {
			$("#CardNum").hide();
			$("#CardHolder").hide();
			$("#IssueDate").hide();
			$("#CCV").hide();
			$("#hide").click(function() {
				$("#CardNum").hide();
				$("#CardHolder").hide();
				$("#IssueDate").hide();
				$("#CCV").hide();
			});
			$("#show").click(function() {
				//debugger
				$("#CardNum").show();
				$("#CardHolder").show();
				$("#IssueDate").show();
				$("#CCV").show();
			});
		});

		$(function() {
			$("#datepicker").datepicker({
				minDate : new Date()
			});
		});

		/* $("#bt")
			.click(
					function() {
						var val = $(
								'input:radio[name="paymentMethod"]:checked')
								.val();

						if (val == null) {
							alert("Please Choose Payment Method!!");
							return false;
						} else if (val == "Credit Card") {
							window
									.showModalDialog("adMetaOpenShow.jsp",
											window,
											"status:no;dialogHeight:210px;dialogWidth:360px;help:no");

						} else {
							alert("Payment will be on Cash!");
						}					
					});
		 */
		function checkip() {

			var tranportType = document.getElementById("TranportType").value
			var pickup = document.getElementById("pickup").value
			var dropoff = document.getElementById("dropoff").value
			var paymentMethod = $('input:radio[name="paymentMethod"]:checked')
					.val();
			var datepicker = document.getElementById("datepicker").value

			if (tranportType == "" || tranportType == null) {
				alert("Please Choose A Transportation Type!!");
				return false;
			}
			if (pickup == "" || pickup == null) {
				alert("Please Choose A Pickup Location!!");
				return false;
			}
			if (dropoff == "" || dropoff == null) {
				alert("Please Choose A Dropoff Location!!");
				return false;
			}
			if (datepicker == "" || datepicker == null) {
				alert("Please Choose A Pickup Date!!");
				return false;
			}
			if (paymentMethod == "" || paymentMethod == null) {
				alert("Please Choose A PaymentMethod!!");
				return false;
			}
			if (paymentMethod == "Credit Card") {
				window
						.showModalDialog("adMetaOpenShow.jsp", window,
								"status:no;dialogHeight:210px;dialogWidth:360px;help:no");
			}
			if (paymentMethod == "Credit Card") {
				//window.location="adMetaOpenShow.jsp";
				//window.createPopup()
				/* var name = prompt("Please enter your name", "")
				if (name != null && name != "") {
					document.write("Hello " + name + "!")
				} */
			} else {
				return true;
			}
		}

		/* function show_popup() {
			var p = window.createPopup()
			var pbody = p.document.body
			pbody.style.backgroundColor = "lime"
			pbody.style.border = "solid black 1px"
			pbody.innerHTML = "This is a pop-up! Click outside to close."
			p.show(150, 150, 200, 50, document.body)
		} */
	</script>
</body>