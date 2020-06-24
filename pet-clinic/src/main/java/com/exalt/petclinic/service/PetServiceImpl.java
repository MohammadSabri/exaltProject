package com.exalt.petclinic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exalt.petclinic.model.Pet;

@Service
public class PetServiceImpl implements PetService {
	private static List<Pet> PetsArray = new ArrayList<Pet>(Arrays.asList(
			new Pet(1, "speedo", 4, "cherasi", 50, 30, "irretation in mouth", 1, "24/1/2019"),
			new Pet(2, "oscar", 2, "bombat", 43, 13, "weigh problem duo to Intestinal bacteria", 2, "1/2/2020"),
			new Pet(3, "roc", 7, "Akita", 90, 45, "hearing bacteria", 3, "1/2/2020"),
			new Pet(4, "speedo", 4, "Azawakh", 30, 30, "tall and weight problem due to ansims", 3, "1/2/2020")));

	@Override
	public Pet create(Pet pet) {
		PetsArray.add(pet);
		return pet;
	}

	@Override
	public Pet update(int id, Pet pet) {
		for (Pet p : PetsArray) {
			if (p.getId() == id) {
				p.setName(pet.getName());
				p.setAge(pet.getAge());
				p.setHeight(pet.getHeight());
				p.setWeight(pet.getWeight());
				p.setSpecies(pet.getSpecies());
				p.setProblemDescribtion(pet.getProblemDescribtion());
				p.setClientId(pet.getClientId());
				return p;
			}
		}
		return null;

	}

	@Override
	public Pet get(int id) {
		return PetsArray.stream().filter(p -> p.getId() == id).findFirst().get();
	}

	@Override
	public List<Pet> getAll(int page, int limit) {

		return PetsArray.stream().skip((long) (page-1)*limit).limit((long) limit).collect(Collectors.toList());
	}

	@Override
	public List<Pet> getClientPets(int id) {
		return PetsArray.stream().filter(p -> p.getClientId() == id).collect(Collectors.toList());
	}

	@Override
	public void delete(int id) {
		PetsArray.removeIf(p -> p.getId() == id);
	}

}
