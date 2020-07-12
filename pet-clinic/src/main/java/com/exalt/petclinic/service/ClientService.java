package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.dto.ClientDto;
import com.exalt.petclinic.dto.ClientUpdateDto;
import com.exalt.petclinic.model.Client;

public interface ClientService {

	Client create(ClientUpdateDto clientUpdateDto);

	ClientDto get(int id);

	List<ClientDto> getAll(int page, int limit);

	ClientUpdateDto update(int id, ClientUpdateDto clientUpdateDto);

	String delete(int id);
}
