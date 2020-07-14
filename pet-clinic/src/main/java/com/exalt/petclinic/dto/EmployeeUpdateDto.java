package com.exalt.petclinic.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.exalt.petclinic.model.Employee.WorkingField;
import com.exalt.petclinic.vallidator.ValidateEmail;
import com.exalt.petclinic.vallidator.ValidatePhoneNumber;
import com.exalt.petclinic.vallidator.ValidateWorkingField;

import io.swagger.annotations.ApiModelProperty;

public class EmployeeUpdateDto {
	@NotNull(message = "the first name must nut be null")
	private String firstName;
	@NotNull(message = "the last name must nut be null")
	private String lastName;
	@NotNull(message = "the huse location must nut be null")
	private String houseLocation;
	@DecimalMin(value = "1000.0", message = "the salary must be >1000.0")
	private Double salary;
	@NotNull(message = "the collegeDegree must nut be null")
	private String collegeDegree;
	@NotNull(message = "the first name must nut be null")
	@ValidateWorkingField(anyOf = { WorkingField.Admin, WorkingField.Owner, WorkingField.Worker })
	@ApiModelProperty(example = "Worker,Admin,Owner" )
	private WorkingField workingField;
	@NotNull(message = "the phone number must nut be null")
	@ValidatePhoneNumber
	private String phoneNumber;
	@Min(value = 0, message = "There is no negative years of experince")
	private Integer yearsOfExperience;
	@ValidateEmail
	private String email;
	@NotNull(message = "the password must nut be null")
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
