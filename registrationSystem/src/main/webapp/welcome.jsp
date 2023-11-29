<body>
<h1>Welcome!</h1>
<!-- Display welcome message or user-specific
content here -->
<%@ page import="java.sql.*" %>
<%@ page import="registry.DBConnection" %>

<%

HttpSession ses=request.getSession();
int id= (Integer) ses.getAttribute("id");
Connection conn=DBConnection.connect();
String query = "SELECT name FROM users WHERE id="+id;
PreparedStatement p = conn.prepareStatement(query);
ResultSet rs=p.executeQuery();
rs.next();
out.print("<p>name: "+rs.getString(1)+"</p>");
out.print("with id: "+id);


%>
</body>