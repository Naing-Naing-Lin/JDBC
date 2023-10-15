package com.jdc.mkt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	/*
	 * --------- Method 1 -----------
	 * Get Connection 
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
	private static final String USER = "root";
	private static final String PASSWORD = "Admin";
	
	public static Connection getConnection1() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	// ------- Method 1 End -----------
	

	
	
	
	/*
	 *  #### Method 2 #####
	 *  Get Connection Using Enum (Not Good)
	 */
	public static Connection getConnection2() throws SQLException {
		return DriverManager.getConnection(Con.URL.getValue(), Con.USER.getValue(), Con.PASSWORD.getValue());
	}
	
	private enum Con{
		URL ("jdbc:mysql://localhost:3306/testdb"),
		USER ("root"),
		PASSWORD ("Admin");
		
		
		private String value;
		
		Con(String value){
			this.value = value;
		}
		
		String getValue() {
			return value;
		}
	}
	
	// ### Method 2 End
}
