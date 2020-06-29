package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:sqlserver://DESKTOP-1JEG9HR\\SQLEXPRESS:1433;databaseName=FFighter;user=sa;password=Admin123"
					);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
