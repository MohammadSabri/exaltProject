package com.exalt.petclinic.model;

import validator.ValidateEmail;

public class Client {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	@ValidateEmail
	private String email;
	private String creationDate;
	private String password;
	public Client() {
		super();
	}

	public Client(int id, String firstName, String lastName, String phoneNumber, String email, String creationDate,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.creationDate = creationDate;
		this.password = password;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
