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

@WebServlet("/RegisterCustomer")
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String warning = "";
		int id = 1;
				
		try {
			String name = request.getParameter("name");
			long account_num = Long.parseLong(request.getParameter("account_num"));
			String email = request.getParameter("mail");
			String password = request.getParameter("password");
			double balance = Double.parseDouble(request.getParameter("balance"));
			long mobile_num =  Long.parseLong(request.getParameter("mobile_no"));
			String address = request.getParameter("address");
			
							//DATABASE CONNCETIVITY CODE
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","projects","projects");
			PreparedStatement ps = con.prepareStatement("select * from sdfc_bank_pro where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				warning = "User Already Exists Please Try With Another Email";
				request.setAttribute("warning",warning);
				RequestDispatcher dispatcher = request.getRequestDispatcher("newCustomer.jsp");
				dispatcher.forward(request, response);
			}else {
				ps = con.prepareStatement("insert into sdfc_bank_pro values(?,?,?,?,?,?,?,?)");
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setLong(3, account_num);
				ps.setString(4, email);
				ps.setString(5,password);
				ps.setDouble(6, balance);
				ps.setLong(7, mobile_num);
				ps.setString(8, address);
				int i = ps.executeUpdate();
				if(i!=0) {
					response.sendRedirect("index.html");
				}
				con.close();
			}			
		}
		catch(NumberFormatException num) {
			warning = "Please Fill All The Values";
			request.setAttribute("warning",warning);
			RequestDispatcher dispatcher = request.getRequestDispatcher("newCustomer.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
