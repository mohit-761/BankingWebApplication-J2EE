
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

@WebServlet("/TransferMoney")
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String warning = "";
		try {

			String password = request.getParameter("password");
			long account_num = Long.parseLong(request.getParameter("account_num"));
			long recipient_account = Long.parseLong(request.getParameter("recipient_acc"));
			double transfer_amount = Double.parseDouble(request.getParameter("transfer_amount"));

			// DATABASE CONNECTION CODE
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "projects", "projects");

			// GETTING CUSTOMERS DETAILS
			PreparedStatement ps = con.prepareStatement("select id,balance from sdfc_bank_pro where account_num = ? and password = ?");
			ps.setLong(1, account_num);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int cid = rs.getInt(1);
			   if(cid == 1) {
				ps = con.prepareStatement("select id, balance from sdfc_bank_pro where account_num = ?");
				ps.setLong(1, recipient_account);
				ResultSet rs2 = ps.executeQuery();
				if (rs2.next()) {
					// complete logic here
					double customers_current_balance = rs.getDouble(2);
					int rid = rs2.getInt(1);
				  if(rid == 1) {
					double recipients_current_balance = rs2.getDouble(2);
					if (customers_current_balance < transfer_amount) {

						warning = "you don't have enough account balance";
						request.setAttribute("warning", warning);
						RequestDispatcher dispatcher = request.getRequestDispatcher("transferMoney.jsp");
						dispatcher.forward(request, response);

					} else {
						// for showing the status of transaction
						double current_balance = customers_current_balance;
						request.setAttribute("current_balance", current_balance);
						customers_current_balance = customers_current_balance - transfer_amount;

						// UPDATAING CUSTOMERS BALANCE
						ps = con.prepareStatement("update sdfc_bank_pro set balance = ? where account_num = ?");
						ps.setDouble(1, customers_current_balance);
						ps.setLong(2, account_num);
						ps.executeUpdate();

						double new_balance = customers_current_balance;
						request.setAttribute("new_balance", new_balance);

						recipients_current_balance = recipients_current_balance + transfer_amount;
						ps = con.prepareStatement("update sdfc_bank_pro set balance = ? where account_num = ?");
						ps.setDouble(1, recipients_current_balance);
						ps.setLong(2, account_num);
						ps.executeUpdate();

						RequestDispatcher dispatcher = request.getRequestDispatcher("transactionStatus.jsp");
						dispatcher.forward(request, response);
					} // inner else unsufficient balance
				  }else {
					    warning = "recipient account does not exists or deleted";
						request.setAttribute("warning", warning);
						RequestDispatcher dispatcher = request.getRequestDispatcher("transferMoney.jsp");
						dispatcher.forward(request, response);
				  }//rid
				} else {
					warning = "recipient account does not exists please check again";
					request.setAttribute("warning", warning);
					RequestDispatcher dispatcher = request.getRequestDispatcher("transferMoney.jsp");
					dispatcher.forward(request, response);
				} // else r2
			}else {
					warning = "Your account does not exist or deleted";
					request.setAttribute("warning", warning);
					RequestDispatcher dispatcher = request.getRequestDispatcher("transferMoney.jsp");
					dispatcher.forward(request, response);
			} // cid
		  } 
			  else {
				warning = "your details are wrong please fill the correct values";
				request.setAttribute("warning", warning);
				RequestDispatcher dispatcher = request.getRequestDispatcher("transferMoney.jsp");
				dispatcher.forward(request, response);
			} //main outer else

			con.close();
		} catch (NumberFormatException e) {
			warning = "please fill all the with correct values";
			request.setAttribute("warning", warning);
			RequestDispatcher dispatcher = request.getRequestDispatcher("transferMoney.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
