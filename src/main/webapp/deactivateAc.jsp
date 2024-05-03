<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./styles/newac.css" />
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
            <li><a href="index.html">Home</a></li>
            <li><a href="newCustomer.jsp">new account</a></li>
            <li><a href="getDetails.jsp">get details</a></li>
            <li><a href="depositeMoney.jsp">deposit</a></li>
            <li><a href="withdrawMoney.jsp">withdraw</a></li>
            <li><a href="transferMoney.jsp">transfer</a></li>
            <li><a class="active" href="deactivateAc.jsp"> close account</a></li>
            <li><a href="about.html">about</a></li>
          </ul>
        </div>
      </div>
    </header>
    <div class="main">
        <div class="card card1">
          <p class="card-title">card</p>
          <img src="./images/chip.png" alt="card-chip" />
          <p class="card-number">
            <span>1234</span> <span>5678</span> <span>9012</span>
            <span>3456</span>
          </p>
          <p>Name</p>
          <p class="expiry">00/00</p>
          <div class="card-logo"></div>
          <div class="card-logo card-logo2"></div>
        </div>
        <form action="DeactivateAc" onsubmit="return false" method="post">
          <section>
            <label for="email">enter email</label>
            <input type="email" name="email" />
          </section>
          <section>
            <label for="account_num">enter account number</label>
            <input type="text" name="account_num" />
          </section>
          <section>
              <label for="password">enter password</label>
              <input type="password" name="password" />
            </section>
          <section class="submit-buttons">
            <button type="submit" onclick="validateForm()">submit</button>
            <button type="reset">clear</button>
          </section>
        </form>
      </div>
       <div class="warning">
       <p class="warning-text">
		   <%if(request.getAttribute("warning") != null) {%>
              <%=request.getAttribute("warning")%>
		   <%}%>
	  </p>
	</div>
       <script>
        function validateForm(){
            let email = document.querySelector("input[name='email']").value;
            let account = document.querySelector("input[name='account_num']").value;
            let password = document.querySelector("input[name='password']").value;
            if(email.length === 0 || account.length === 0 || password.length === 0){
                document.querySelector(".warning-text").textContent = "please fill all the values";
                return false;
            }
            else{
            	document.querySelector("form").setAttribute("onsubmit","return true");
            }
        }
      </script> 
  </body>
</html>