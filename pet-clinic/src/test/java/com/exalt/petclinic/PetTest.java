package com.exalt.petclinic;
import java.util.Date;

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
		Pet pet=new Pet(2, "testDB_spring", 4, "cherasi", 50, 30, "irretation in mouth", 7, new Date(2020, 2, 4));
		//petRepository.save(pet);
		
}
}