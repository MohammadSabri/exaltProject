package com.exalt.petclinic;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

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
		Client  c= new Client(7, "fdgfdg", "sgdfgdgab", "1234567890", "wasfi@fsdfs.com", new Date(2020, 2, 4), "123456789");
		clientRepository.findAll();

		
}
	
}
