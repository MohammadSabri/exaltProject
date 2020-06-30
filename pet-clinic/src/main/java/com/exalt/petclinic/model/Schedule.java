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

@Entity
@Table(name = "schedual")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double price;
	@Column(name = "problem_describtion")
	private String problemDescribtion;
	@Column(name = "medical_describtion")
	private String medicalDescribtion;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Schedule() {
		super();
	}

	public Schedule(int id, double price, String problemDescribtion, String medicalDescribtion, Date date, Pet pet,
			Employee employee) {
		super();
		this.id = id;
		this.price = price;
		this.problemDescribtion = problemDescribtion;
		this.medicalDescribtion = medicalDescribtion;
		this.date = date;
		this.pet = pet;
		this.employee = employee;
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

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", price=" + price + ", problemDescribtion=" + problemDescribtion
				+ ", medicalDescribtion=" + medicalDescribtion + ", date=" + date + ", pet=" + pet + ", employee="
				+ employee + "]";
	}

}
