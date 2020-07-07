package com.exalt.petclinic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pet")
@JsonIgnoreProperties({ "client", "schedule" })

public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@NotNull()
	private String name;
	@Min(0)
	@NotNull()
	private int age;
	@NotNull()
	private String species;
	@NotNull()
	@Min((long) 1.0)
	private double height;
	@NotNull()
	private double weight;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyy")
	@Column(name = "creation_date")
	private Date creationDate;
	@ManyToOne()
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Schedule> schedule = new ArrayList<Schedule>();

	public Pet() {
		super();
	}

	public Pet(int id, String name, int age, String species, double height, double weight, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.species = species;
		this.height = height;
		this.weight = weight;
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", species=" + species + ", height=" + height
				+ ", weight=" + weight + ", creationDate=" + creationDate + ", client=" + client + "]";
	}

}
