package com.exalt.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.exalt.petclinic.vallidator.ValidateEmail;

@Entity
@Table(name = "employee")
public class Employee {
	public enum WorkingField {
		Owner, Admin, Worker
	}

	@NotNull
	@Min(value = 0, message = "the id must by >1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(name = "first_name")
	private String firstName;
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Column(name = "house_location")
	private String houseLocation;
	@NotNull
	@Min(value = 200, message = "no salary less than 200")
	private Double salary;
	@NotNull
	@Column(name = "collage_degree")
	private String collegeDegree;
	@NotNull
	@Column(name = "working_field")
	@Enumerated(EnumType.STRING)
	private WorkingField workingField;
	@NotNull
	@Column(name = "phone_number")
	private String phoneNumber;
	@NotNull
	@Column(name = "year_of_experience")
	private Integer yearsOfExperience;
	@NotNull
	@Column(name = "creation_date")
	private String creationDate;
	@NotNull
	private String password;
	@NotNull
	@ValidateEmail
	private String email;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Schedule> schedule = new ArrayList<Schedule>();

	public Employee() {
		super();
	}

	public Employee(Integer id, String firstName, String lastName, String houseLocation, Double salary,
			String collegeDegree, WorkingField workingField, String phoneNumber, Integer yearsOfExperience,
			String creationDate, String password, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.houseLocation = houseLocation;
		this.salary = salary;
		this.collegeDegree = collegeDegree;
		this.workingField = workingField;
		this.phoneNumber = phoneNumber;
		this.yearsOfExperience = yearsOfExperience;
		this.creationDate = creationDate;
		this.password = password;
		this.email = email;
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

	public String getHouseLocation() {
		return houseLocation;
	}

	public void setHouseLocation(String houseLocation) {
		this.houseLocation = houseLocation;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getCollegeDegree() {
		return collegeDegree;
	}

	public void setCollegeDegree(String collegeDegree) {
		this.collegeDegree = collegeDegree;
	}

	public WorkingField getWorkingField() {
		return workingField;
	}

	public void setWorkingField(WorkingField workingField) {
		this.workingField = workingField;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", houseLocation="
				+ houseLocation + ", salary=" + salary + ", collegeDegree=" + collegeDegree + ", workingField="
				+ workingField + ", phoneNumber=" + phoneNumber + ", yearsOfExperience=" + yearsOfExperience
				+ ", creationDate=" + creationDate + ", password=" + password + ", email=" + email + "]";
	}
	

}
