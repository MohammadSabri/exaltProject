package com.exalt.petclinic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.DTO.ClientDto;
import com.exalt.petclinic.DTO.ClientUpdateDto;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.service.ClientService;

@RestController
public class ClientController {
	@Autowired
	private ClientService clientService;

	/**
	 * get all the client with page and limit restriction
	 * 
	 * @param request
	 * @param page
	 * @param limit
	 * @return
	 */
	@GetMapping(path = "/api/v1/clients", params = { "page", "limit" })
	public List<ClientDto> getClients(HttpServletRequest request, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		return (clientService.getAll(page, limit));
	}

	/**
	 * get the client with specific id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/api/v1/client/{id}", produces = "application/json")
	public ClientDto getClient(@PathVariable int id) {

		return (clientService.get(id));
	}

	/**
	 * add client to the data base
	 * 
	 * @param client
	 * @return
	 */
	@PostMapping(path = "/api/v1/client", consumes = "application/json", produces = "application/json")

	public Client addClient(@Valid @RequestBody ClientUpdateDto clientUpdateDto) {

		return clientService.create(clientUpdateDto);
	}

	/**
	 * 
	 * @param client
	 * @param id
	 * @return
	 */
	@PutMapping(path = "/api/v1/client/{id}", consumes = "application/json", produces = "application/json")
	public ClientUpdateDto updateClient(@Valid @RequestBody ClientUpdateDto clientUpdateDto, @PathVariable int id) {
		return clientService.update(id, clientUpdateDto);
	}

	/**
	 * delete the client from the dataBase
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/api/v1/client/{id}")
	public String deleteClient(@PathVariable int id) {

		return clientService.delete(id);
	}
}
