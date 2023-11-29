package registry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//retrieve form data
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Connection conn =DBConnection.connect();
			String query = "insert into users(name, email, password)values(?,?,?)";
			PreparedStatement pstmt =conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			
			resp.sendRedirect("confirmation.jsp");
		}catch (SQLException e){
			resp.getWriter().println(e.getMessage());
			e.printStackTrace();}
	}
}