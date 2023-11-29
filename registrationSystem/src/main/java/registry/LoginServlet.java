package registry;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws IOException{
		String email = rq.getParameter("email");
		String password = rq.getParameter("password");
		try {
			Connection conn =DBConnection.connect();
			String query = "select * from users where email = ? AND password = ?";
			
			PreparedStatement pstmt =conn.prepareStatement(query);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int id=rs.getInt(1);
			//authentication successful
				HttpSession mySession=rq.getSession();
				mySession.setAttribute("id",id);
			rp.sendRedirect("welcome.jsp");
			}else {
			//authentication failed
				DBConnection.close();
				rp.sendRedirect("login.jsp");
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}