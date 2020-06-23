package com.exalt.petclinic.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Pet {
	@Min(value=1,message="can not creat pet with this id, id must be >1")
	@NotNull()
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
	@Min((long) 1.0)
	@NotNull()
	private double weight;
	private String problemDescribtion;
	@Min(1)
	@NotNull()
	private int clientId;
	@NotNull()
	private String creationDate;
	
	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pet(int id,String name, int age, String species, double height, double weight, String problemDescribtion,
			int clientId, String creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.species = species;
		this.height = height;
		this.weight = weight;
		this.problemDescribtion = problemDescribtion;
		this.clientId = clientId;
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

	public String getProblemDescribtion() {
		return problemDescribtion;
	}

	public void setProblemDescribtion(String problemDescribtion) {
		this.problemDescribtion = problemDescribtion;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}



	public String getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
}
