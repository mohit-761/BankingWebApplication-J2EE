
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawMoney")
public class WithdrawMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String warning = "";
		try {

			String email = request.getParameter("mail");
			String password = request.getParameter("password");
			long account_num = Long.parseLong(request.getParameter("account_num"));
			double withdraw_amount = Double.parseDouble(request.getParameter("withdraw_amount"));

			// DATABASE CONNECTION CODE
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "projects", "projects");
			PreparedStatement ps = con.prepareStatement(
					"select id,balance from sdfc_bank_pro where email = ? and account_num = ? and password = ?");
			ps.setString(1, email);
			ps.setLong(2, account_num);
			ps.setString(3, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double id = rs.getInt(1);
			   if(id==1) {
				double balance = rs.getDouble(2);
				out.println("<h1>" + balance + "</h1>");

				if (balance < withdraw_amount) {
					request.setAttribute("warning", "You Don't Have Sufficient Balance");
					RequestDispatcher dispatcher = request.getRequestDispatcher("withdrawMoney.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("current_balance", balance);
					balance = balance - withdraw_amount;
					ps = con.prepareStatement(
							"update sdfc_bank_pro set balance = ? where email = ? and account_num = ? and password = ?");
					request.setAttribute("new_balance", balance);
					ps.setDouble(1, balance);
					ps.setString(2, email);
					ps.setLong(3, account_num);
					ps.setString(4, password);
					int i = ps.executeUpdate();
					if (i == 1) {
						request.setAttribute("status", "Transaction Successfull");
						RequestDispatcher dispatcher = request.getRequestDispatcher("transactionStatus.jsp");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("warning", "Transaction Failed Please try again");
						RequestDispatcher dispatcher = request.getRequestDispatcher("withdrawMoney.jsp");
						dispatcher.forward(request, response);
					}
				} // inner else
			   } // id==1
			   else {
				  warning = "user does not exists";
				  request.setAttribute("warning", warning);
				  RequestDispatcher dispatcher = request.getRequestDispatcher("withdrawMoney.jsp");
				  dispatcher.forward(request, response);
			   }
			} else {
				warning = "User Does not exists please fill the correct values";
				request.setAttribute("warning", warning);
				RequestDispatcher dispatcher = request.getRequestDispatcher("withdrawMoney.jsp");
				dispatcher.forward(request, response);
			}
			con.close();
		} catch (NumberFormatException e) {
			warning = "please fill all the with correct values";
			request.setAttribute("warning", warning);
			RequestDispatcher dispatcher = request.getRequestDispatcher("withdrawMoney.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
