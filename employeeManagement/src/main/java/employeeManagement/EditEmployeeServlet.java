package employeeManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/edit")
public class EditEmployeeServlet extends HttpServlet { 
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String query = "update employees  set name=?, designation=?, salary=?   where id = ?"; 
@Override 
protected void doGet(HttpServletRequest req, HttpServletResponse  resp) throws IOException { 
	//get PrintWriter 
	PrintWriter pw = resp.getWriter(); 
	//set content type 
	resp.setContentType("text/html"); 
	// get the id of record 
	int id = Integer.parseInt(req.getParameter("id")); 
	//get the edited data 
	String Name = req.getParameter("Name"); 
	String Designation = req.getParameter("Designation"); 
	float Salary = Float.parseFloat(req.getParameter("Salary")); //load the jdbc driver 
	try { 
		Class.forName("com.mysql.cj.jdbc.Driver"); 
	} catch (ClassNotFoundException cnf) { 
		cnf.printStackTrace(); 
	} 
	//generate the connection 
	try { 
		Connection conn = DriverManager.getConnection( 
		 "jdbc:mysql://localhost:3306/employeedatabase",   "root",  
		"admin$1Admin"); 
		PreparedStatement ps = conn.prepareStatement(query); 
		ps.setString(1, Name); 
		ps.setString(2, Designation); 
		ps.setFloat(3, Salary); 
		ps.setInt(4, id); 
		int count = ps.executeUpdate(); 
		if(count == 1) { 
			pw.println("<h2>Record is edited  successfully.</h2>"); 
		}else { 
			pw.println("<h2>Record edition failed miserably.</h2>");
		} 
	} catch (SQLException se) { 
		se.printStackTrace(); 
		pw.println("<h1>" + se.getMessage() + "</h1>"); 
	} catch (Exception e) { 
		e.printStackTrace(); 
		pw.println("<h1>" + e.getMessage() + "</h1>"); 
	} 
	pw.println("<a href='home.html'>Home</a>"); 
	pw.print("<br>"); 
	pw.println("<a href='employeelist'>View Employees</a>"); 
	} 
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse  resp) throws ServletException, IOException { 
		doGet(req, resp); 
	} 
}