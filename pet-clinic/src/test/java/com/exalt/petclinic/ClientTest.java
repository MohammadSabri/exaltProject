package com.exalt.petclinic;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Client;
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
		Client  c= new Client(10, "fdgfdg", "sgdfgdgab", "9876543210", "wasfi@fsdfs.com", new Date(2020, 2, 4), "123456789");
		clientRepository.save(c);
		
}
	
}
