<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Your details are here | never share your password with
	anyone</title>
<link rel="stylesheet" href="./styles/newac.css" />
<link rel="stylesheet" href="./styles/userDetails.css" />
</head>
<body>
	<header>
		<div class="navbar">
			<ul>
				<li class="logo"><img src="./images/bank logo.png"
					alt="logo of sdfc bank" /></li>
				<li>
					<ul class="navbar-sublist">
						<li class="heading">SDFC Bank</li>
						<li class="subheading">Extraordinary services</li>
					</ul>
				</li>
			</ul>

			<div class="navbar-links">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="newCustomer.jsp">new account</a></li>
					<li><a class="active" href="getDetails.jsp">get details</a></li>
					<li><a href="depositeMoney.jsp">deposite</a></li>
					<li><a href="withdrawMoney.jsp">withdraw</a></li>
					<li><a href="transferMoney.jsp">transefer</a></li>
					<li><a href="deactivateAc.jsp"> close ac</a></li>
					<li><a href="about.html">about</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div class="main">
		<div>
			<table border="1">
				<tr>
					<% int columnCount = (int)request.getAttribute("columnCount");
						for(String user: request.getAttribute("userDetails"))
					  %>
					
				</tr>
<!-- 				<tr>
					<td>Active</td>
					<td>Mohit</td>
					<td>1234567890</td>
					<td>9876543210</td>
					<td>mohit@gmail.com</td>
					<td>123450.00</td>
					<td>mohit@123</td>
				</tr> -->
			</table>
		</div>
	</div>
</body>
</html>
