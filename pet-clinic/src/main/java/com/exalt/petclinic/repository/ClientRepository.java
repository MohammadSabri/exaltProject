package com.exalt.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exalt.petclinic.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
	
}
