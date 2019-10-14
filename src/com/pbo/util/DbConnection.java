package com.pbo.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private static Connection connection;
	
	static {
		final String URL = "jdbc:mysql://localhost:3306/db_bank";
		final String USER = "root";
		final String PASSWORD = "";
		try {
		connection = DriverManager.getConnection(URL, USER, 
				PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
