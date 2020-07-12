package com.exalt.petclinic.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.exalt.petclinic.vallidator.ValidateEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties({"roles","schedule"})

public class Employee {
	public enum WorkingField {
		Owner, Admin, Worker
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "house_location")
	private String houseLocation;
	
	@Min(value = 200, message = "no salary less than 200")
	private Double salary;
	
	@Column(name = "college_degree")
	private String collegeDegree;
	
	@Column(name = "working_field")
	@Enumerated(EnumType.STRING)
	private WorkingField workingField;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "years_of_experience")
	private Integer yearsOfExperience;
	@Column(name = "creation_date")
	private Date creationDate;
	@NotNull
	private String password;
	
	@ValidateEmail
	private String email;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Schedule> schedule = new ArrayList<Schedule>();

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "employee_role", joinColumns = {
			@JoinColumn(name = "employee_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles;

	public Employee() {
		super();
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", houseLocation="
				+ houseLocation + ", salary=" + salary + ", collegeDegree=" + collegeDegree + ", workingField="
				+ workingField + ", phoneNumber=" + phoneNumber + ", yearsOfExperience=" + yearsOfExperience
				+ ", creationDate=" + creationDate + ", password=" + password + ", email=" + email + "]";
	}

}
