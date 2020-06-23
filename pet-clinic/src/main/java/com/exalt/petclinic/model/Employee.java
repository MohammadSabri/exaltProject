package com.exalt.petclinic.model;


public class Employee {
public enum WorkingField { Owner,Admin, Worker }

private int id;
private String firstName;
private String lastName;
private String houseLocation;
private double salary;
private String collegeDegree;
private WorkingField workingField; 
private String phoneNumber;
private int yearsOfExperience;
private String creationDate;
private String password;
public Employee(int id, String firstName, String lastName, String houseLocation, double salary, String collegeDegree,
		WorkingField workingField, String phoneNumber, int yearsOfExperience, String creationDate, String password) {
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
public String getHouseLocation() {
	return houseLocation;
}
public void setHouseLocation(String houseLocation) {
	this.houseLocation = houseLocation;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
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
public int getYearsOfExperience() {
	return yearsOfExperience;
}
public void setYearsOfExperience(int yearsOfExperience) {
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

}
