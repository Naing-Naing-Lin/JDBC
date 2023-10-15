package com.jdc.mkt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {

	private int id;
	@NonNull
	private String street;
	@NonNull
	private String township;
	@NonNull
	private String city;
	private boolean active;
}
