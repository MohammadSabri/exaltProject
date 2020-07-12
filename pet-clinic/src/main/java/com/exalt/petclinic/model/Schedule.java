package com.exalt.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "schedual")
@JsonIgnoreProperties({ "employee", "pet" })
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double price;
	@Column(name = "problem_describtion")
	private String problemDescribtion;
	@Column(name = "medical_describtion")
	private String medicalDescribtion;
	@NotNull
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "reservation_date")
	private Date reservationDate;
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Schedule() {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
