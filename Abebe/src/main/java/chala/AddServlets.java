package chala;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


public class AddServlets extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int n1 = Integer.parseInt(req.getParameter("num1"));
		int n2 = Integer.parseInt(req.getParameter("num2"));
		
		PrintWriter pw = res.getWriter();
		pw.println(n1+n2);
		
	}

}
