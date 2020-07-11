package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.DTO.ClientDto;
import com.exalt.petclinic.DTO.ClientUpdateDto;
import com.exalt.petclinic.model.Client;

public interface ClientService {
	
	Client create(ClientUpdateDto clientUpdateDto);
	ClientUpdateDto update(int id, ClientUpdateDto clientUpdateDto);
	ClientDto get(int id);
	List<ClientDto> getAll(int page, int limit);
	String delete (int id);
}
