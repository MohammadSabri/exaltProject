package com.exalt.petclinic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.ClientRepository;

@SpringBootTest
public class ClientTest {
	@Autowired
	ClientRepository clientRepository;
	@Test
	void contextLoads() {
		System.out.print("hello");
	}
	@Test
	void addClient() {
		Client client =new Client();
		client.setFirstName("mohamamd");
		client.setLastName("sabri");
		client.setEmail("mohadmdad.khaledsabri@outlook.com");
		client.setPhoneNumber("0533929492");
		client.setPassword("1234567890");
		client.setCreationDate(new Date(2005,4,2));
		List<Pet> list =new ArrayList<Pet>();
	//	client.setId(1);
		
		Pet pet=new Pet();
		pet.setName("sree");
		pet.setSpecies("cherasi");
		pet.setAge(5);
		pet.setHeight(1.35);
		pet.setWeight(23.5);
		pet.setCreationDate(new Date(2005,4,2));
		
	//	pet.setId(1);
		list.add(pet);
		pet.setClient(client);
		

		Pet pet2=new Pet();
		//pet2.setId(2);

		pet2.setName("sree");
		pet2.setSpecies("cherasi");
		pet2.setAge(5);
		pet2.setHeight(1.35);
		pet2.setWeight(23.5);
		pet2.setCreationDate(new Date(2005,4,2));
		pet2.setClient(client);

		list.add(pet2);
		client.setPets(list);

		clientRepository.save(client);
		
}
	@Test
	void getClient() {
		Client client =clientRepository.findById(1).get(0);
		for (Pet p : client.getPets()) {
			System.out.println(p.toString());
			
		}
		
	}
	
	@Test
	void deleteClient() {
		clientRepository.deleteById(2);
			
		}
	
}
