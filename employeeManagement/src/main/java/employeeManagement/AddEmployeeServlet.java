package employeeManagement;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/register")
public class AddEmployeeServlet extends HttpServlet {  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override 
	 protected void doGet( 
	HttpServletRequest req, 
	HttpServletResponse resp) throws IOException{ 
	 //get PrintWriter 
	 PrintWriter pw = resp.getWriter(); 
	//set content type 
	 resp.setContentType("text/html"); 
	   
	 //get the book info 
	 String name = req.getParameter("Name"); 
	 String designation = req.getParameter("Designation");
	 float salary = Float.parseFloat(req.getParameter("Salary"));
	  
	 //load the jdbc driver 
	 try { 
	 Class.forName("com.mysql.jdbc.Driver:");   
	 } catch (ClassNotFoundException cnf) { 
	 cnf.printStackTrace(); 
	 } 
	  
	 //generate the connection 
	 try { 
	 Connection conn = 
	DriverManager.getConnection( 
	"jdbc:mysql://localhost:3306/employeedatabase", 
	"root", 
	"admin$1Admin"); 
	 PreparedStatement ps = conn.prepareStatement(query); 
	  
	 ps.setString(1, name); 
	 ps.setString(2, designation); 
	 ps.setFloat(3, salary); 
	   
	 int count = ps.executeUpdate(); 
	   
	 if(count == 1) { 
	  pw.println("<h2> Employee registered successfully.</h2>"); } 
	 else { 
	  pw.println("<h2> Employee registered Failed.</h2>"); }
	 } catch (Exception e) { 
		 e.printStackTrace(); 
		 pw.println("<h1>" + e.getMessage() + "</h1>"); } 
		 pw.println("<a href='home.html'>Home</a>"); pw.print("<br>"); 
		 pw.println("<a href='employeelist'>View employees List</a>");}
	  
	 @Override
	 protected void doPost( 
	HttpServletRequest req, 
	HttpServletResponse resp) throws IOException { 
	 doGet(req, resp); 
	 }

private static final String query = 
"insert into employees(name, designation, salary)  values(?, ?, ?)";
	}
