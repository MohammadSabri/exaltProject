package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.PetRepository;

@SpringBootTest
public class PetTest {

	@Autowired
	PetRepository petRepository;

	@Test
	void testAddPet() {
		Pet pet =new Pet();
		pet.setName("sree");
		pet.setSpecies("cherasi");
		pet.setAge(5);
		pet.setHeight(1.35);
		pet.setWeight(23.5);
		pet.setCreationDate(new Date(2020,4,1));
		Client client =new Client();
		client.setId(1);// this step to set the id of the client 
		pet.setClient(client);
		petRepository.save(pet);

	}

	@Test
	void testDeletePet() {

		petRepository.deleteById(5);

	}

	@Test
	void testReadPet() {
		
		Pet pet = petRepository.findById(3).get();
		System.out.println(pet.getName());
		assertEquals("testGet", pet.getSpecies());
		
	}

	@Test
	void testUpdatePet() {
		Pet pet = petRepository.findById(3).get();
		pet.setName("testUpdateSQL");
		petRepository.save(pet);


	}
	@Test
	void testGetByName() {
		
		List<Pet>petList= petRepository.findByName("dse");
		petList.forEach(p->System.out.println(p.getName()));
		
		
	}
	@Test
	void testGetByNameAndAge() {
		
		List<Pet>petList= petRepository.findByNameAndAge("dse", 4);
		petList.forEach(p->System.out.println(p.getId()));
		
		
	}

	@Test
	void testGetByAgeGreaterThan() {

		List<Pet> petList = petRepository.findByAgeGreaterThan(3);
		petList.forEach(p -> System.out.println(p.getId()));

	}
	
	@Test
	void testGetByweightBetween() {
		
		List<Pet>petList= petRepository.findByWeightBetween(41.0, 54.0);
		petList.forEach(p->System.out.println(p.getId()));
		
		
	}
	@Test
	void testGetByNameQuery() {
		
		List<Pet>petList= petRepository.findAllPet("dse");
		petList.forEach(p->System.out.println(p.getId()));
		
		
	}

	@Test
	void testGetAllNQ() {

		List<Pet> petList = petRepository.findAllPetNQ("dse");
		petList.forEach(p -> System.out.println(p.toString()));

	}
	@Test
	void testDelete() {

		petRepository.deleteById(2);

	}

}