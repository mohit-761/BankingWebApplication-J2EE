<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./styles/details.css" />
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
					<li><a href="getDetails.jsp">get details</a></li>
					<li><a href="depositeMoney.jsp">deposit</a></li>
					<li><a class="active" href="withdrawMoney.jsp">withdraw</a></li>
					<li><a href="transferMoney.jsp">transfer</a></li>
					<li><a href="deactivateAc.jsp"> close account</a></li>
					<li><a href="about.html">about</a></li>
				</ul>
			</div>
		</div>
	</header>

	<div class="main">

		<div class="card">
			<p class="card-title">card</p>
			<img src="./images/chip.png" alt="card-chip" />
			<p class="card-number">
				<span>1234</span> <span>5678</span> <span>9012</span> <span>3456</span>
			</p>
			<p>Name</p>
			<p class="expiry">00/00</p>
			<div class="card-logo"></div>
			<div class="card-logo card-logo2"></div>
		</div>

		<form action="WithdrawMoney" method="post">
			<section>
				<label for="mail">enter your email:</label> 
				<input type="email" name="mail" />
			</section>
			<section>
				<label for="account_num">account number:</label>
				<input type="text" name="account_num" />
			</section>
			<section>
				<label for="password">enter password:</label>
				<input type="password" name="password" />
			</section>
			<section>
				<label for="withdraw_amount">enter amount to withdraw:</label>
				<input type="text" name="withdraw_amount" />
			</section>
			<section class="submit-buttons">
				<button type="submit">submit</button>
				<button type="reset">clear</button>
			</section>
		</form>
	</div>
	<div class="warning">
		<%if(request.getAttribute("warning")!= null){ %>
		<p class="warning-text"><%= request.getAttribute("warning") %></p>
		<%} %>
	</div>
	<script>
      document.querySelector("button[type='submit']").addEventListener("click", (event) => {
        
          const pass = document.querySelector("input[type='password']").value;
          const mail = document.querySelector("input[name='mail']").value;
          const acno = document.querySelector("input[name='account_num']").value;
          const amount = document.querySelector("input[name='withdraw_amount']").value;
          if(pass.length === 0 || mail.length === 0 || acno.length === 0 || amount.length === 0){
              document.querySelector(".warning-text").textContent="please fill all the fields";
              event.preventDefault();
             }
        });
    </script>
</body>
</html>