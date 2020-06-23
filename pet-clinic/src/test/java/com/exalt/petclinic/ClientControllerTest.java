package com.exalt.petclinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.service.ClientServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	ClientServiceImpl clientServiceImpl;
	
	@Test
	void testurl() {
		int id=1;
		 URI targetUrl= UriComponentsBuilder.fromUriString("/api/v1/client/{id}")                          
		            .queryParam("id", id)                               
		            .build()                                                
		            .encode()                                               
		            .toUri();
		 Client testClient =this.restTemplate.getForObject(targetUrl, Client.class);
		 System.out.println(testClient.getEmail());
		 /*
		 Client tClient =clientServiceImpl.get(id);
		 assertAll(
					() -> assertEquals(testClient.getId(),tClient.getId()),
					() -> assertEquals(testClient.getFirstName(),tClient.getFirstName()),
					() -> assertEquals(testClient.getLastName(), tClient.getLastName()),
					() -> assertEquals(testClient.getPhoneNumber(), tClient.getPhoneNumber()),
					() -> assertEquals(testClient.getEmail(), tClient.getEmail()),
					() -> assertEquals(testClient.getCreationDate(), tClient.getCreationDate()),
					() -> assertEquals(testClient.getPassword(), tClient.getPassword())
			);		
	*/

	}
}
