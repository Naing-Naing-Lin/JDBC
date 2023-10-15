package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.JDBC_Prepared_Statement_Demo;
import com.jdc.mkt.JDBC_Statement_Demo;

@TestMethodOrder(OrderAnnotation.class)
public class DataTest {
	
	static JDBC_Statement_Demo demo;
	static JDBC_Prepared_Statement_Demo demo2;

	@BeforeAll
	static void init() {
		demo = new JDBC_Statement_Demo();
		demo2 = new JDBC_Prepared_Statement_Demo();
	}

	@Test
	@Order(1)
	void testData() throws SQLException {
		demo.truncateTable();
		var list = demo.selectData();
		
		assertEquals(2, list.size());
		list.forEach(System.out::println);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Susan", "Willian"})
	@Order(2)
	void testInsertDataWithStatement(String name) {
		var row = demo.insertData(name);
		assertEquals(1, row);
	}

	@ParameterizedTest
	@ValueSource(strings = {"John", "Snow"})
	@Order(3)
	void testInsertDataWithPreparedStatement(String name) {
		var row = demo2.insertData(name);
		assertEquals(1, row);
	}
	
}
