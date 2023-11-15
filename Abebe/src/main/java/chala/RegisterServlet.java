package chala;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {  @Override 
	 protected void doGet( 
	HttpServletRequest req, 
	HttpServletResponse resp) throws IOException{ 
	 //get PrintWriter 
	 PrintWriter pw = resp.getWriter(); 
	//set content type 
	 resp.setContentType("text/html"); 
	   
	 //get the book info 
	 String bookName = req.getParameter("bookName"); 
	 String bookEdition = req.getParameter("bookEdition");
	 float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
	  
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
	"jdbc:mysql://localhost:3306/bookregister", 
	"root", 
	"admin$1Admin"); 
	 PreparedStatement ps = conn.prepareStatement(query); 
	  
	 ps.setString(1, bookName); 
	 ps.setString(2, bookEdition); 
	 ps.setFloat(3, bookPrice); 
	   
	 int count = ps.executeUpdate(); 
	   
	 if(count == 1) { 
	  pw.println("<h2> Book registered successfully.</h2>"); } 
	 else { 
	  pw.println("<h2> Book Not registered successfully.</h2>"); }
	 } catch (Exception e) { 
		 e.printStackTrace(); 
		 pw.println("<h1>" + e.getMessage() + "</h1>"); } 
		 pw.println("<a href='Home.html'>Home</a>"); pw.print("<br>"); 
		 pw.println("<a href='booklist'>Book List</a>");}
	  
	 @Override
	 protected void doPost( 
	HttpServletRequest req, 
	HttpServletResponse resp) throws IOException { 
	 doGet(req, resp); 
	 }

private static final String query = 
"insert into books(bookname, bookedition, bookprice)  values(?, ?, ?)";
	}