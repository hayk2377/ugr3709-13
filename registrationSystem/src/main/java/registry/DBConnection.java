package registry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection cn;
	public static Connection connect() throws SQLException {
		String DB_URL ="jdbc:mysql://localhost:3306/userdb";
		String DB_USER = "root";
		String DB_PASSWORD = "admin$1Admin";
		if(cn==null) {cn=DriverManager.getConnection(
				DB_URL,
				DB_USER,
				DB_PASSWORD);}
		return cn;
	}
	public static void close() throws SQLException {
		cn.close();
		cn=null;
		
	}

}
