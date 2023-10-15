package com.jdc.mkt.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.service.CustomerService;

public class CustomerDtoTest {

	static CustomerService custService;
	
	@BeforeAll
	static void init() {
		custService = new CustomerService();
	}
	
	@ParameterizedTest
	@ValueSource(strings = "mintharrkyi")
	void customerCountByHotelName(String name) {
		
		var list = custService.getCustomers(name);
		list.forEach(c -> System.out.printf("%5s\t\t%d", c.name(), c.count()).println());
	}
}
