package com.exalt.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.exalt.petclinic.vallidator.ValidateEmail;
@Entity
@Table(name = "client")
public class Client {
	@NotNull
	@Min(value=0,message = "the id must by >1")
	@Id
	private Integer id;
	@NotNull
	@Column(name="first_name")
	private String firstName;
	@NotNull
	@Column(name="last_name")
	private String lastName;
	@NotNull
	@Min(value=10,message = "The phone number consist of 10 digits")
	@Column(name="phone_number", unique = true)
	private String phoneNumber;
	@ValidateEmail()
	@NotNull
	@Column(name="email", unique = true)
	private String email;
	@NotNull
	@Column(name="creation_date")
	private Date creationDate;
	@NotNull
	@Column(name="password")
	private String password;
	
	public Client() {
		super();
	}

	public Client(Integer id, String firstName, String lastName, String phoneNumber, String email, Date creationDate,
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
