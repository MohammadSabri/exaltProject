package com.exalt.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.exalt.petclinic.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
	
}
