package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.dto.ClientMapper;
import com.exalt.petclinic.dto.ClientUpdateDto;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.service.ClientService;

@SpringBootTest
public class ClientTest {
	@Autowired
	ClientService clientService;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	EntityManager entityManager;
	private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

	@Test
	@DisplayName(value = "testClientCreate_AddNewClient_successfull")

	void addClient() {

		ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
		clientUpdateDto.setFirstName("Test Create First Name");
		clientUpdateDto.setLastName("Test Create Last Name");
		clientUpdateDto.setEmail("Test@CreateMail.com");
		clientUpdateDto.setPhoneNumber("0599123765");
		clientUpdateDto.setPassword("TestPaswword");

		Client client = clientMapper.updateDtoToClient(clientUpdateDto);
		client.setCreationDate(Calendar.getInstance().getTime());
		Client testClient = clientService.create(clientUpdateDto);

		assertAll(() -> assertNotNull(testClient.getId()), () -> assertNotNull(testClient.getCreationDate()),
				() -> assertEquals(testClient.getFirstName(), client.getFirstName()),
				() -> assertEquals(testClient.getEmail(), client.getEmail()),
				() -> assertEquals(testClient.getLastName(), client.getLastName()),
				() -> assertEquals(testClient.getPassword(), client.getPassword()),
				() -> assertEquals(testClient.getPhoneNumber(), client.getPhoneNumber())

		);

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
