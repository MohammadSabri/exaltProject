package com.exalt.petclinic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.ClientRepository;

@SpringBootTest
public class ClientTest {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {

	}
	@Test
	void addClient() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 4, 2, 4, 13);
		Date date = calendar.getTime();

		Client client = new Client();
		client.setFirstName("mohamamd");
		client.setLastName("sabri");
		client.setEmail("mohadd.khaledsabri@outlook.com");
		client.setPhoneNumber("0533333492");
		client.setPassword("1234567890");
		client.setCreationDate(date);

		List<Pet> list = new ArrayList<Pet>();
		// client.setId(1);

		Pet pet = new Pet();
		pet.setName("sree");
		pet.setSpecies("cherasi");
		pet.setAge(5);
		pet.setHeight(1.35);
		pet.setWeight(23.5);
		pet.setCreationDate(date);

		list.add(pet);
		pet.setClient(client);

		Pet pet2 = new Pet();

		pet2.setName("sree");
		pet2.setSpecies("cherasi");
		pet2.setAge(5);
		pet2.setHeight(1.35);
		pet2.setWeight(23.5);
		pet2.setCreationDate(date);
		pet2.setClient(client);

		list.add(pet2);
		client.setPets(list);

		clientRepository.save(client);

	}

	@Test
	void getClient() {
		Client client = clientRepository.findById(1).get();
		System.out.println(client.toString());
	}

	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED)
	void getClientTransaction() {
		// Client client = clientRepository.findById(4).get();
		clientRepository.deleteById(4);

		clientRepository.findById(4);

	}

	@Test
	void deleteClient() {
		clientRepository.deleteById(2);

	}

	@Test
	@Transactional

	void testLevele1CacheEvict() {
		Session session = entityManager.unwrap(Session.class);
		Client client = clientRepository.findById(1).get();
		clientRepository.findById(1);
		session.evict(client);
		clientRepository.findById(1);
	}

	@Test
	void testBCrypt() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 4, 2, 4, 13);
		Date date = calendar.getTime();
		System.out.println(date.toString());
		System.out.println(Calendar.getInstance().getTime());
		// String s = "123456789";
		// BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

		// String crypt = bCryptPasswordEncoder.encode(s);
		// System.out.println(crypt);

		// System.out.println(bCryptPasswordEncoder.matches("123456789", crypt));
	}

}
