package project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName = QLLinhKien", "sa", "123456");
		return conn; 
	}
}
