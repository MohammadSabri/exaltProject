package com.exalt.petclinic.DTO;

import java.util.Date;

public class ScheduleDto {
	private int id;
	private double price;
	private String problemDescribtion;
	private String medicalDescribtion;
	private Date date;
	private int petId;
	private int employeeId;

	public ScheduleDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProblemDescribtion() {
		return problemDescribtion;
	}

	public void setProblemDescribtion(String problemDescribtion) {
		this.problemDescribtion = problemDescribtion;
	}

	public String getMedicalDescribtion() {
		return medicalDescribtion;
	}

	public void setMedicalDescribtion(String medicalDescribtion) {
		this.medicalDescribtion = medicalDescribtion;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
