package com.exalt.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.PetRepository;

@SpringBootTest
public class PetTest {
	
	@Autowired
	PetRepository petRepository;
	@Test
	void contextLoads() {
		System.out.print("hello");
	}
	@Test
	void addPet() {
		Pet pet =new Pet(7, "testDB", 4, "cherasi", 50, 30, "irretation in mouth", 1, "2/1/2019");
		petRepository.save(pet);
}
}