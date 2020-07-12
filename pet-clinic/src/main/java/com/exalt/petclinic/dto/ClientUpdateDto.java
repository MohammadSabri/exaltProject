package com.exalt.petclinic.dto;

import javax.validation.constraints.NotNull;

import com.exalt.petclinic.vallidator.ValidateEmail;
import com.exalt.petclinic.vallidator.ValidatePhoneNumber;

public class ClientUpdateDto {
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@ValidatePhoneNumber()
	private String phoneNumber;
	@ValidateEmail()
	@NotNull
	private String email;
	@NotNull
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
