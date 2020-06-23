package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.service.ClientServiceImpl;

@SpringBootTest
public class ClientServiceImplTest {
	@Autowired
	private ClientServiceImpl clientServiceImpl;

	@Test
	@DisplayName(value = "testClientCreate_AddNewClient_successfull")
	void testCreat() {
		int id=8;
		Client client = new Client(id, "test", "class", "123456789", "test@class", "24/3/2001", "123456789");
		clientServiceImpl.create(client);
		Client testClient =clientServiceImpl.get(id);
		assertAll(
				() -> assertEquals(testClient.getId(), client.getId()),
				() -> assertEquals(testClient.getFirstName(), client.getFirstName()),
				() -> assertEquals(testClient.getLastName(), client.getLastName()),
				() -> assertEquals(testClient.getPhoneNumber(), client.getPhoneNumber()),
				() -> assertEquals(testClient.getEmail(), client.getEmail()),
				() -> assertEquals(testClient.getCreationDate(), client.getCreationDate()),
				() -> assertEquals(testClient.getPassword(), client.getPassword())
		);	
		
	}

	@Test
	void testDelete() {
		int id = 2;
		boolean testExist = false;
		clientServiceImpl.delete(id);
		for (Client c : clientServiceImpl.getAll(0, 100)) {
			if (c.getId() == id) {
				testExist = true;
				break;
			}

		}
		assertFalse(testExist);
	}

	@Test
	void testUpdate() {
		int id = 1;
		Client client = new Client(id, "moh", "sab", "0592573952", "ewwew@fsdfs", "1/2/2018", "123456789");
		Client test = clientServiceImpl.get(id);
		assertAll(() -> assertEquals(test.getFirstName(), client.getFirstName()),
				() -> assertEquals(test.getLastName(), client.getLastName()),
				() -> assertEquals(test.getPhoneNumber(), client.getPhoneNumber()),
				() -> assertEquals(test.getEmail(), client.getEmail()),
				() -> assertEquals(test.getPassword(), client.getPassword())
		);
	}
}
