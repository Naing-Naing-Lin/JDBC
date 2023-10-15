package com.jdc.mkt;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.utils.MySqlConnection;

/*
 * Statement interface cannot accept parameters and useful 
 * when you are using static SQL statements at runtime. 
 * If you want to run SQL query only once then this interface is preferred 
 * over PreparedStatement. 
 */

public class JDBC_Statement_Demo {
	
	public List<String> selectData() throws SQLException{
		var list = new ArrayList<String>();
		String query = "select * from data_tbl";
		
		try (Statement stmt = MySqlConnection.getConnection1().createStatement()) {
			// use executeQuery for select data from table
			var rs = stmt.executeQuery(query); // Return type is ResultSet
			
			while(rs.next()) {
				String s = rs.getString("name");
				list.add(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;		
	}
	
	public int insertData(String data) {
		String query = "insert into data_tbl (name) values ('%s')".formatted(data);
		var count = 0;
		try (Statement stmt = MySqlConnection.getConnection1().createStatement()) {
			// use executeUpdate for insert, update, delete
			count = stmt.executeUpdate(query);
			return count;			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void truncateTable() {
		String query1 = "truncate table data_tbl";
		String query2 = "insert into data_tbl (name) values ('Aung Aung'), ('Su Su')";
		try (Statement stmt = MySqlConnection.getConnection1().createStatement()) {
			stmt.addBatch(query1);
			stmt.addBatch(query2);
			stmt.executeBatch();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
