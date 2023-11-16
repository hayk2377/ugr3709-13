package employeeManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employeelist")
public class ViewEmployeesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String query = "select id, name, designation, salary from employees"; 
@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/employeedatabase", "root", "admin$1Admin");
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			pw.println("<table>");
			pw.println("<p>our employees are</p>");
			pw.println("<tr>");
			pw.println("<th>Employee Id</th>");
			pw.println("<th>Employee Name</th>");
			pw.println("<th>Employee designation</th>");
			pw.println("<th> Salary</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>" + rs.getInt(1) + "</td>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				pw.println("<td>" + rs.getFloat(4) + "</td>");
				pw.println("<td> <a href=\"delete?id="+rs.getInt(1) + "\">delete</a></td>");
				pw.println("<td> <a href=\"editScreen?id="+rs.getInt(1) + "\">edit</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");	
			
		
		}catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		} 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}
}
