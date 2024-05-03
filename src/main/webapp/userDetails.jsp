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
					<li><a href="depositeMoney.jsp">deposit</a></li>
					<li><a href="withdrawMoney.jsp">withdraw</a></li>
					<li><a href="transferMoney.jsp">transfer</a></li>
					<li><a href="deactivateAc.jsp"> close account</a></li>
					<li><a href="about.html">about</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div class="main">
		<div>
			<%session.getAttribute("email"); %>
			<table border="1">
				<tr>
					<%
					String[] columns = (String[]) request.getAttribute("columnName");
					String[] userData = (String[]) request.getAttribute("userDetails");
					for (String col : columns) {
					%>
					<th><%=col%></th>
					<%
					}
					%>
				</tr>
				<tr>
					<%for (String user : userData) {%>					
					<td><%=user%></td>
					<%}%>
				</tr>
			</table>
		</div>
		<div>
		 <a class="logout" href="Logout">logout</a>
		</div>
	</div>
</body>
</html>
