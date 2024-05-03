
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

@WebServlet("/DeactivateAc")
public class DeactivateAc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			long account_num = Long.parseLong(request.getParameter("account_num"));
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "projects", "projects");
			PreparedStatement ps = con.prepareStatement("select * from sdfc_bank_pro where email = ? and account_num = ? and password = ?");

			ps.setString(1, email);
			ps.setLong(2, account_num);
			ps.setString(3, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt(1);

				if (id != 1) {
					request.setAttribute("warning", "account does not exists");
					RequestDispatcher dispatcher = request.getRequestDispatcher("deactivateAc.jsp");
					dispatcher.forward(request, response);
					
				} else {
					ps = con.prepareStatement("update sdfc_bank_pro set id = 0 where email = ? and account_num = ?");
					ps.setString(1, email);
					ps.setLong(2, account_num);

					int i = ps.executeUpdate();
					if (i == 1) {
						request.setAttribute("warning", "account deleted successfully");
					} else {
						request.setAttribute("warning", "account does not deleted");
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("deactivateAc.jsp");
					dispatcher.forward(request, response);
				}
				
			} else {
				request.setAttribute("warning", "please enter correct details");
				RequestDispatcher dispatcher = request.getRequestDispatcher("deactivateAc.jsp");
				dispatcher.forward(request, response);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
