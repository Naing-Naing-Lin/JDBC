package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.service.AddressService;
import com.jdc.mkt.service.CrudOperation;

public class AddressCrudTest {

	static CrudOperation<Address> addressService;
	
	@BeforeAll
	static void init() {
		addressService = new AddressService();
	}
	
	@Test
	@Order(1)
	@Disabled
	void insert() {
		var address = new Address("35st", "chanayetharzan","mdy");
		int row = addressService.insert(address);
		
		assertEquals(1, row);
	}
	
	@Test
	@Order(2)
	@Disabled
	void update() {
		var address = new Address("22st", "AungZaya","Yangon");
		address.setId(7);
		int row = addressService.update(address);
		
		assertEquals(1, row);
	}
	
	@Test
	@Order(3)
	@Disabled
	void delete() {
		var address = new Address();
		address.setActive(false);
		address.setId(7);
		int row = addressService.delete(address);
		
		assertEquals(1, row);
	}
	
	@Test
	@Order(4)
	void select() {
		var address = new Address();
		address.setCity("Yangon");
		address.setTownship("Hlaing");
		var list = addressService.select(address);
		
		assertEquals(1, list.size());
	}
}
