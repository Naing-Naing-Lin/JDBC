package com.jdc.mkt.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hotel {

	private int id;
	private String name;
	private int numRooms;
	private Contact contact;
	private Address address;
	private boolean active;
}
