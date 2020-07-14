package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.exalt.petclinic.dto.ClientDto;
import com.exalt.petclinic.dto.ClientMapper;
import com.exalt.petclinic.dto.ClientUpdateDto;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.service.ClientService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ClientTest {
	@LocalServerPort
    int localPort=8001;
	@Autowired
	ClientService clientService;
	@Autowired
	ClientRepository clientRepository;

	private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
	int id = 0;

	@BeforeEach
	void setupBefor() {

		ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
		clientUpdateDto.setFirstName("Test");
		clientUpdateDto.setLastName("Test");
		clientUpdateDto.setEmail("Test@Befall.com");
		clientUpdateDto.setPhoneNumber("0599123762");
		clientUpdateDto.setPassword("TestPaswword");
		Client client = clientMapper.updateDtoToClient(clientUpdateDto);
		client.setCreationDate(Calendar.getInstance().getTime());
		Client testClient = clientService.create(clientUpdateDto);
		id = testClient.getId();
		System.out.println("the id in befor " + id);
	}

	@AfterEach
	void setupAfter() {
		clientRepository.deleteAll();

	}

	@Test
	@DisplayName(value = "testClientCreate_AddNewClient_successfull")
	void addClientTest() {

		ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
		clientUpdateDto.setFirstName("Test Create First Name");
		clientUpdateDto.setLastName("Test Create Last Name");
		clientUpdateDto.setEmail("Test@Creat.com");
		clientUpdateDto.setPhoneNumber("0599123777");
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
	void getClientTest() {
		ClientDto clientDto = clientService.get(id);
	
		assertAll(() -> assertNotNull(id), () -> assertNotNull(clientDto.getCreationDate()),
				() -> assertEquals("Test", clientDto.getFirstName()),
				() -> assertEquals("Test@Befall.com", clientDto.getEmail()),
				() -> assertEquals("Test", clientDto.getLastName()),
				() -> assertEquals("TestPaswword", clientDto.getPassword()),
				() -> assertEquals("0599123762", clientDto.getPhoneNumber())

		);
	}

	@Test
	void deleteClientTest() {

		assertEquals("Client Deleted sucsessfuly", clientService.delete(id));

	}

	@Test
	void updateClientTest() {

		ClientUpdateDto clientUpdateDto = clientMapper.dtoToUpdateDto(clientService.get(id));
		clientUpdateDto.setFirstName("testUpdate");
		clientUpdateDto.setLastName("testUpdate");
		clientUpdateDto.setEmail("TsesUpdate@test.com");
		clientUpdateDto.setPassword("testUpdatePAssword");
		clientUpdateDto.setPhoneNumber("0590000000");

		clientService.update(id, clientUpdateDto);

		ClientDto clientDto = clientService.get(id);
		assertAll(() -> assertNotNull(id), () -> assertNotNull(clientDto.getCreationDate()),
				() -> assertEquals(clientDto.getFirstName(), clientUpdateDto.getFirstName()),
				() -> assertEquals(clientDto.getEmail(), clientUpdateDto.getEmail()),
				() -> assertEquals(clientDto.getLastName(), clientUpdateDto.getLastName()),
				() -> assertEquals(clientDto.getPassword(), clientUpdateDto.getPassword()),
				() -> assertEquals(clientDto.getPhoneNumber(), clientUpdateDto.getPhoneNumber())

		);

	}

	@Test

	void testRestTemplat() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		try {
			
			System.out.println("---------------------------------");

			ResponseEntity<ClientDto> response = testRestTemplate.
					getForEntity(new URI("http://localhost:8001/api/v1/client/"+id), ClientDto.class);
			
			System.out.println(response.getBody().getFirstName());
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

	}

//	@Test
//	void testBCrypt() {
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2020, 4, 2, 4, 13);
//		Date date = calendar.getTime();
//		System.out.println(date.toString());
//		System.out.println(Calendar.getInstance().getTime());
//		// String s = "123456789";
//		// BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
//
//		// String crypt = bCryptPasswordEncoder.encode(s);
//		// System.out.println(crypt);
//
//		// System.out.println(bCryptPasswordEncoder.matches("123456789", crypt));
//	}

}
