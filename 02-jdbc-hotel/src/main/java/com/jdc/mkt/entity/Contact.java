package com.jdc.mkt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Contact {

	private int id;
	@NonNull
	private String primaryContact;
	@NonNull
	private String secondaryContact;
	@NonNull
	private String email;
	private boolean active;
}
