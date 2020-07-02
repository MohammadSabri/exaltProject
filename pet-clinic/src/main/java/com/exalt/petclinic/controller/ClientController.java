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

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.service.ClientService;

@RestController
public class ClientController {
	@Autowired
	private ClientService clientService;
	@Autowired
	ClientRepository clientRepository;

	@GetMapping(path = "/api/v1/clients", params = { "page", "limit" })
	public List<Client> getClients(HttpServletRequest request, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		if (page < 0) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 0) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}

		return (clientService.getAll(page, limit));
	}

	@GetMapping(path = "/api/v1/client/{id}", produces = "application/json")
	public Client getClient(@PathVariable int id) {
		
		 return (clientService.get(id));

	}

	@PostMapping(path = "/api/v1/client", consumes = "application/json", produces = "application/json")

	public Client addClient(@Valid @RequestBody Client client) {

		return clientService.create(client);
	}

	@PutMapping(path = "/api/v1/client/{id}", consumes = "application/json", produces = "application/json")
	public void updateClient(@RequestBody Client client, @PathVariable int id) {
		clientService.update(id, client);
	}

	@DeleteMapping("/api/v1/client/{id}")
	public String deleteClient(@PathVariable int id) {

		return clientService.delete(id);
		

	}
}
