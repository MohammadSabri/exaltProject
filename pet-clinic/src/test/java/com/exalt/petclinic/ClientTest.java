package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestClientException;

import com.exalt.petclinic.dto.ClientDto;
import com.exalt.petclinic.dto.ClientMapper;
import com.exalt.petclinic.dto.ClientUpdateDto;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.service.ClientService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ClientTest {

	@Autowired
	ClientService clientService;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	TestRestTemplate testRestTemplate;

	private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

	@BeforeEach
	void setupBefor() throws RestClientException, URISyntaxException {

		ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
		clientUpdateDto.setFirstName("Test");
		clientUpdateDto.setLastName("Test");
		clientUpdateDto.setEmail("Test@Befall.com");
		clientUpdateDto.setPhoneNumber("0599123762");
		clientUpdateDto.setPassword("TestPaswword");
		create(clientUpdateDto);

	}

	@AfterEach
	void setupAfter() {
		clientRepository.deleteAll();
	}

	@Test
	@DisplayName(value = "testClientCreate_AddNewClient_successfull")
	void addClientTest() throws RestClientException, URISyntaxException {

		ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
		clientUpdateDto.setFirstName("Test Create First Name");
		clientUpdateDto.setLastName("Test Create Last Name");
		clientUpdateDto.setEmail("Test@Creat.com");
		clientUpdateDto.setPhoneNumber("0599123777");
		clientUpdateDto.setPassword("TestPaswword");

		Client respClient = create(clientUpdateDto);

		assertAll(() -> assertNotNull(respClient.getId()), () -> assertNotNull(respClient.getCreationDate()),
				() -> assertEquals(respClient.getFirstName(), clientUpdateDto.getFirstName()),
				() -> assertEquals(respClient.getEmail(), clientUpdateDto.getEmail()),
				() -> assertEquals(respClient.getLastName(), clientUpdateDto.getLastName()),
				() -> assertEquals(respClient.getPassword(), clientUpdateDto.getPassword()),
				() -> assertEquals(respClient.getPhoneNumber(), clientUpdateDto.getPhoneNumber())

		);

	}

	@Test
	@DisplayName(value = "testClientGet_GetExistClient_successfull")
	void getClientTest() throws RestClientException, URISyntaxException {
		ClientDto clientDto = get();
		assertAll(() -> assertNotNull(getId()), () -> assertNotNull(clientDto.getCreationDate()),
				() -> assertEquals("Test", clientDto.getFirstName()),
				() -> assertEquals("Test@Befall.com", clientDto.getEmail()),
				() -> assertEquals("Test", clientDto.getLastName()),
				() -> assertEquals("TestPaswword", clientDto.getPassword()),
				() -> assertEquals("0599123762", clientDto.getPhoneNumber())

		);

	}

	@Test
	@DisplayName(value = "testClientGetAll_GetAllClient_successfull")
	void getAllClientTest() throws RestClientException, URISyntaxException {

	}

	@Test
	@DisplayName(value = "testClientDelete_DeleteClient_successfull")
	void deleteClientTest() {
		try {
			assertEquals("Client Deleted sucsessfuly", delete());

		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

	@Test
	@DisplayName(value = "testClientUpdate_UpdateClient_successfull")
	void updateClientTest() throws RestClientException, URISyntaxException {
		ClientUpdateDto clientUpdateDto = clientMapper.dtoToUpdateDto(clientService.get(getId()));
		clientUpdateDto.setFirstName("testUpdate");
		clientUpdateDto.setLastName("testUpdate");
		clientUpdateDto.setEmail("TsesUpdate@test.com");
		clientUpdateDto.setPassword("testUpdatePAssword");
		clientUpdateDto.setPhoneNumber("0590000000");

		update(clientUpdateDto);
		ClientDto clientDto = get();
		assertAll(() -> assertNotNull(getId()), () -> assertNotNull(clientDto.getCreationDate()),
				() -> assertEquals(clientDto.getFirstName(), clientUpdateDto.getFirstName()),
				() -> assertEquals(clientDto.getEmail(), clientUpdateDto.getEmail()),
				() -> assertEquals(clientDto.getLastName(), clientUpdateDto.getLastName()),
				() -> assertEquals(clientDto.getPassword(), clientUpdateDto.getPassword()),
				() -> assertEquals(clientDto.getPhoneNumber(), clientUpdateDto.getPhoneNumber())

		);

	}

	private ClientDto get() throws RestClientException, URISyntaxException {
		return testRestTemplate.getForEntity(new URI("http://localhost:8001/api/v1/client/" + getId()), ClientDto.class)
				.getBody();
	}

	private Client create(ClientUpdateDto clientUpdateDto) throws RestClientException, URISyntaxException {
		return testRestTemplate
				.postForEntity(new URI("http://localhost:8001/api/v1/client"), clientUpdateDto, Client.class).getBody();
	}

	private String delete() throws RestClientException, URISyntaxException {
		return testRestTemplate.exchange(
				new RequestEntity<>(HttpMethod.DELETE, new URI("http://localhost:8001/api/v1/client/" + getId())),
				String.class).getBody();
	}

	private ClientUpdateDto update(ClientUpdateDto clientUpdateDto) throws RestClientException, URISyntaxException {

		return testRestTemplate.exchange(new RequestEntity<>(clientUpdateDto, HttpMethod.PUT,
				new URI("http://localhost:8001/api/v1/client/" + getId())), ClientUpdateDto.class).getBody();
	}

	private List<ClientDto> getAll(int page, int limit) throws RestClientException, URISyntaxException {

		return testRestTemplate
				.exchange(new URI("http://localhost:8001/api/v1/client?page=" + page + "&limit=" + limit),
						HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientDto>>() {
						})
				.getBody();
	}

	private int getId() {
		return clientRepository.findClientIdNQ();
	}

	private void addTenClient() throws RestClientException, URISyntaxException {
		ClientUpdateDto clientUpdateDto = new ClientUpdateDto();

		for (int i = 0; i < 10; i++) {
			clientUpdateDto.setFirstName("Test Create First Name");
			clientUpdateDto.setLastName("Test Create Last Name");
			clientUpdateDto.setPassword("TestPaswword");
			clientUpdateDto.setEmail("Test" + i + "@Creat.com");
			clientUpdateDto.setPhoneNumber("059912377" + i);
			create(clientUpdateDto);
		}

	}
}
