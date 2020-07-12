package com.exalt.petclinic.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PetUpdateDto {
	@NotNull(message = "The name must not be null")
	private String name;
	@Min(value = 1, message = "age must be >=1")
	private int age;
	@NotNull(message = "The Species field must be writen")
	private String species;
	@DecimalMin(value = "0.1", message = "The min height is 0.1 meter")
	private double height;
	@DecimalMin(value = "0.1", message = "The min Weight is 0.1 Kg")
	private double weight;
	private int clientId;

	public PetUpdateDto() {
		super();
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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
