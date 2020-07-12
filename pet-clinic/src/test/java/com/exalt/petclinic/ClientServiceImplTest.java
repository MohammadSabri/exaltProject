package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.dto.ClientDto;
import com.exalt.petclinic.dto.ClientMapper;
import com.exalt.petclinic.dto.ClientUpdateDto;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.service.ClientService;

@SpringBootTest
public class ClientServiceImplTest {
	@Autowired
	ClientService clientService;
	@Autowired
	EntityManager entityManager;
	private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

	@Test
	@DisplayName(value = "testClientCreate_AddNewClient_successfull")
	void addClientTest() {

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
	@DisplayName(value = "testClientGet_GetClientById_successfull")
	void getClientTest() {
		int id = 1;
		ClientDto clientDto = new ClientDto();
		clientDto.setFirstName("mohammad");
		clientDto.setLastName("sabri");
		clientDto.setEmail("mohammdad.khaledsabri@outlook.com");
		clientDto.setPhoneNumber("0592770916");
		clientDto.setPassword("mm32k4");
		clientDto.setId(id);
		ClientDto clientDtoTest = clientService.get(id);
		assertAll(() -> assertEquals(clientDtoTest.getId(), id), () -> assertNotNull(clientDtoTest.getCreationDate()),
				() -> assertEquals(clientDtoTest.getFirstName(), clientDto.getFirstName()),
				() -> assertEquals(clientDtoTest.getEmail(), clientDto.getEmail()),
				() -> assertEquals(clientDtoTest.getLastName(), clientDto.getLastName()),
				() -> assertEquals(clientDtoTest.getPassword(), clientDto.getPassword()),
				() -> assertEquals(clientDtoTest.getPhoneNumber(), clientDto.getPhoneNumber())

		);
	}

	@Test
	@DisplayName(value = "testClientGetAll_GetAllClient_successfull")
	void getAllClientTest() {
		int page = 100;
		int limit = 100;
		List<ClientDto> clientDtos = clientService.getAll(page, limit);
		assertTrue(clientDtos.isEmpty());
		clientDtos.clear();
		page = 1;
		limit = 2;
		clientDtos = clientService.getAll(page, limit);
		assertTrue(clientDtos.size() == 2);
		clientDtos.clear();
		page = 1;
		limit = 1;
		clientDtos = clientService.getAll(page, limit);

		ClientDto clientDto = new ClientDto();
		clientDto.setFirstName("mohammad");
		clientDto.setLastName("sabri");
		clientDto.setEmail("mohammdad.khaledsabri@outlook.com");
		clientDto.setPhoneNumber("0592770916");
		clientDto.setPassword("mm32k4");
		clientDto.setId(1);
		ClientDto clientDtoTest = clientDtos.get(0);

		assertAll(() -> assertEquals(clientDtoTest.getId(), 1), () -> assertNotNull(clientDtoTest.getCreationDate()),
				() -> assertEquals(clientDtoTest.getFirstName(), clientDto.getFirstName()),
				() -> assertEquals(clientDtoTest.getEmail(), clientDto.getEmail()),
				() -> assertEquals(clientDtoTest.getLastName(), clientDto.getLastName()),
				() -> assertEquals(clientDtoTest.getPassword(), clientDto.getPassword()),
				() -> assertEquals(clientDtoTest.getPhoneNumber(), clientDto.getPhoneNumber())

		);
	}

	@Test
	@DisplayName(value = "testClientUpdate_UpdateClientById_successfull")
	void updateClientTest() {

	}

	@Test
	@DisplayName(value = "testClientDelete_DeleteClientById_successfull")
	void delteClientTest() {
		int id = 12;
		assertEquals("Client Deleted sucsessfuly", clientService.delete(id));
	}
}
