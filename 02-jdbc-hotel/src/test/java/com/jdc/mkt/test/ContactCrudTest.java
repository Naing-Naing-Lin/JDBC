package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Contact;
import com.jdc.mkt.service.ContactService;
import com.jdc.mkt.service.CrudOperation;

public class ContactCrudTest {

	static CrudOperation<Contact> contactService;
	
	@BeforeAll
	static void init() {
		contactService = new ContactService();
	}
	
	@Test
	@Order(1)
	@Disabled
	void insert() {
		var contact = new Contact("09123456", "09112244","someone@gmail.com");
		int row = contactService.insert(contact);
		
		assertEquals(1, row);
	}
	
	@Test
	@Order(2)
	@Disabled
	void update() {
		var contact = new Contact("09112233", "09112244","someone@gmail.com");
		contact.setId(13);
		int row = contactService.update(contact);
		
		assertEquals(1, row);
	}
	
	@Test
	@Order(3)
	@Disabled
	void delete() {
		var contact = new Contact();
		contact.setActive(false);
		contact.setId(13);
		int row = contactService.delete(contact);
		
		assertEquals(1, row);
	}
}
