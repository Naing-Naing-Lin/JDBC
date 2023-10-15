package com.jdc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USER = "root";
	private static final String PASSWORD = "Admin";
	
	
	// Method 1
	public static Connection getConnection1() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	

	
	
	// Method 2
	public static Connection getConnection2() throws SQLException {
		return DriverManager.getConnection(URL + "?user=root&password=Admin");
	}
	
	
	
	
	// Method 3
	public static Connection getConnection3() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWORD);
		return DriverManager.getConnection(URL, prop);
	}
}
