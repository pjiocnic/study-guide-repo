package com.example.flattener;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	private int id;
	private String name;
	@JsonProperty("PASS_PRT") // 👈 Add this
	private Passport passprt;
	private List<Automobile> automobile;
	private Address address;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Passport getPassprt() {
		return passprt;
	}

	public List<Automobile> getAutomobile() {
		return automobile;
	}

	public Address getAddress() {
		return address;
	}
}
