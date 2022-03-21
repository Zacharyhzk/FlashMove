<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/register.css">
		<title>Register Page</title>
	</head>
<body>

	<div id="header" style="width:100; height:20%; vertical-align:center">
		<div style="text-align:left;width:100%;display:inline-block;vertical-align:middle;text-align:center">
			<img src="images/logo_blue.png" style="width:48%;margin-top:2%">
		</div>
	</div>
	
	<div id="content">
		<div style="text-align:center;margin-top:8%;font-size:20px">Register</div>
		<form action="RegisterServlet" method="GET" style="text-align:center">
			<div style="height:10%">
				<%-- <span style="text-align:right;width:5%;margin-left:20%">User ID  : </span> --%>
				<input type="text" name="userName" size="20" class="input" placeholder="USER NAME">
			</div>
			<div style="height:10%">
				<%-- <span style="text-align:right;width:5%;margin-left:17%">Password : </span> --%>
				<input type="password" name="password" size="20" class="input" placeholder="PASSWORD">
			</div>
			<div style="height:10%">
				<%-- <span style="text-align:right;width:5%;margin-left:17%">Password : </span> --%>
				<input type="password" name="password" size="20" class="input" placeholder="COMFIRM PASSWORD">
			</div>
			<div style="height:10%">
				<input type="text" name="nickName" size="20" class="input" placeholder="NICK NAME">
			</div>
			<div style="height:10%">
				<input type="text" name="email" size="20" class="input" placeholder="USER EMAIL">
			</div>
			<div style="height:10%">
				<input type="text" name="phone" size="20" class="input" placeholder="USER PHONE">
			</div>
			<div><input type="submit" value="Sign  Up" class="submit"></div>
		</form>
	</div>
</body>
</html>