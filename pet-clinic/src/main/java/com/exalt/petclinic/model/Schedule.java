package com.exalt.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "schedual")
public class Schedule {
	@Id
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

}
