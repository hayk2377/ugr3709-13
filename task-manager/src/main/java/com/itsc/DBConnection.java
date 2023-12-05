package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static public Connection connect() throws SQLException{

		String url = "jdbc:mysql://localhost:3306/task_manager";
		String userName = "abebe";
		String password = "abebe";
		
		return DriverManager.getConnection(url, userName, password);
	}

}
