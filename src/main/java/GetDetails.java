
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GetDetails")
public class GetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String warning = "";

		try {

			String email = request.getParameter("mail");
			long account_num = Long.parseLong(request.getParameter("account_num"));
			String password = request.getParameter("password");
			
			// CREATING CONNECTION USING
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "projects", "projects");
			PreparedStatement ps = con.prepareStatement("select * from sdfc_bank_pro where account_num = ? and password = ? and email = ?");
			ps.setLong(1, account_num);
			ps.setString(2, password);
			ps.setString(3, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				if(id==1) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				String[] userDetails = new String[columnCount];
				String[] columnName = new String[columnCount];
				
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				for(int i = 1; i<= columnCount; i++) {
					userDetails[i-1] = rs.getString(i);
					columnName[i-1] = rsmd.getColumnName(i);
				}
				request.setAttribute("columnCount", columnCount);
				request.setAttribute("columnName", columnName);
				request.setAttribute("userDetails", userDetails);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./userDetails.jsp");
				dispatcher.forward(request, response);
				}else {
					warning = "Account does not exits or deleted";
					request.setAttribute("warning", warning);
					RequestDispatcher dispatcher = request.getRequestDispatcher("getDetails.jsp");
					dispatcher.forward(request, response);
				}// id
			} else {
				warning = "Result Not Found Please Enter Correct Details";
				request.setAttribute("warning", warning);
				RequestDispatcher dispatcher = request.getRequestDispatcher("getDetails.jsp");
				dispatcher.forward(request, response);
			}
			con.close();
		} catch (NumberFormatException e) {
			warning = "Please Fill All The Values";
			request.setAttribute("warning", warning);
			RequestDispatcher dispatcher = request.getRequestDispatcher("getDetails.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
