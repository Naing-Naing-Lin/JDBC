package com.jdc.mkt;

import com.jdc.utils.MySqlConnection;
/*
 *It is used when you want to use SQL statements many times. 
 *The PreparedStatement interface accepts input parameters at runtime. 
 */
public class JDBC_Prepared_Statement_Demo {

	public int insertData (String name) {
		String query = "insert into data_tbl (name) values (?)";
		
		try(var con = MySqlConnection.getConnection1();
				var stmt = con.prepareStatement(query)) {
			stmt.setString(1, name);
			return stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
