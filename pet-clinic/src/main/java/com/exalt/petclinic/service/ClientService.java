package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.model.Client;

public interface ClientService {
	
	Client create(Client client);
	Client update(int id, Client client);
	Client get(int id);
	List<Client> getAll(int page, int limit);
	String delete (int id);
}
