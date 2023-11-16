package employeeManagement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/editScreen") 
public class EditEmployeeScreen extends HttpServlet { 
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String query =  "select name,designation, salary   from employees   where id = ?"; 
@Override 
protected void doGet(HttpServletRequest req, HttpServletResponse  resp) throws IOException{ 
//get PrintWriter 
PrintWriter pw = resp.getWriter(); 
//set content type 
resp.setContentType("text/html"); 
// get the id of record 
int id = Integer.parseInt(req.getParameter("id")); 
//load the jdbc driver 
try { 
Class.forName("com.mysql.cj.jdbc.Driver"); 
} catch (ClassNotFoundException cnf) { 
cnf.printStackTrace(); 
} 
//generate the connection 
try { 
Connection conn =  
 DriverManager.getConnection( 
 "jdbc:mysql://localhost:3306/employeedatabase", "root", "admin$1Admin"); 
PreparedStatement ps = conn.prepareStatement(query); 
ps.setInt(1, id); 
ResultSet rs = ps.executeQuery(); 
rs.next(); 
pw.println("<form action = 'edit?id="+id+"' method = 'post'>"); pw.println("<table>"); 
pw.println("<tr>"); 
pw.println("<td>Book Name</td>"); 
pw.println("<td><input type = 'text', name =  'Name', value = '" + rs.getString(1)+"'</td>"); 
pw.println("</tr>"); 
pw.println("<tr>"); 
pw.println("<td>Book Edition</td>"); 
pw.println("<td><input type = 'text', name =  'Designation', value = '" + rs.getString(2)+"'</td>"); pw.println("</tr>"); 
pw.println("<tr>"); 
pw.println("<td>Book Price</td>"); 
pw.println("<td><input type = 'text', name =  'Salary', value = '" + rs.getFloat(3)+"'</td>"); 
pw.println("</tr>"); 
pw.println("<tr>"); 
pw.println("<td><input type = 'submit' value =  'Edit'></td>"); 
pw.println("<td><input type = 'reset' value =  'Cancel'></td>"); 
pw.println("</tr>"); 
pw.println("</table>"); 
pw.println("</form>");} catch (SQLException se) { 
se.printStackTrace(); 
pw.println("<h1>" + se.getMessage() + "</h1>"); 
} catch (Exception e) { 
e.printStackTrace(); 
pw.println("<h1>" + e.getMessage() + "</h1>"); 
} 
pw.println("<a href='home.html'>Home</a>"); 
} 
@Override 
protected void doPost(HttpServletRequest req, HttpServletResponse  resp) throws IOException{ 
doGet(req, resp); 
} 
}
