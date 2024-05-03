<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Status Of Your Transaction</title>
<link rel="stylesheet" href="./styles/details.css" />
<link rel="stylesheet" href="./styles/status.css" />
</head>
<body>
    <header>
        <div class="navbar">
          <ul>
            <li class="logo">
              <img src="./images/bank logo.png" alt="logo of sdfc bank" />
            </li>
            <li>
              <ul class="navbar-sublist">
                <li class="heading">SDFC Bank</li>
                <li class="subheading">Extraordinary services</li>
              </ul>
            </li>
          </ul>
  
          <div class="navbar-links">
            <ul>
              <li><a class="active" href="index.html">Home</a></li>
              <li><a href="newCustomer.jsp">new account</a></li>
              <li><a href="getDetails.jsp">get details</a></li>
              <li><a href="depositeMoney.jsp">deposit</a></li>
              <li><a href="withdrawMoney.jsp">withdraw</a></li>
              <li><a href="transferMoney.jsp">transfer</a></li>
              <li><a href="deactivateAc.jsp"> close account</a></li>
              <li><a href="about.html">about</a></li>
            </ul>
          </div>
        </div>
      </header>
      <section class="main">
       <div class="card-container">
         <div class="card card1">
             <p class="card-title">card</p>
             <img src="./images/chip.png" alt="card-chip">
             <p class="card-number"><span>1234</span> <span>5678</span> <span>9012</span> <span>3456</span></p>
             <p>Name</p>
             <p class="expiry">00/00</p>
             <div class="card-logo"></div>
             <div class="card-logo card-logo2"></div>
          </div>
      </div> <!--card-container-->
      <div class="status">
        <h1>Transaction Successful</h1>
        <h2>Old Balance: <%= request.getAttribute("current_balance")%></h2>
        <h2>Current Balance: <%= request.getAttribute("new_balance") %></h2>
    </div>
      </section>
</body>
</html>