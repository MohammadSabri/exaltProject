package com.exalt.petclinic.DTO;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ScheduleUpdateDto {
	@DecimalMin(value = "1", message = "The price cant be for free")
	private double price;
	@NotNull(message = "Please write a problem description")
	private String problemDescribtion;
	@NotNull(message = "Please write a medical description")
	private String medicalDescribtion;
	@NotNull
	@Min(value = 1, message = "pet id must be >0")
	private int petId;
	@NotNull
	@Min(value = 1, message = "Employee id must be >0")
	private int employeeId;
	private Date reservationDate;

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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

}
