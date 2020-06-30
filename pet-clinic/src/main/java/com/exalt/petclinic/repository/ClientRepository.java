package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Pet;

public interface ClientRepository extends CrudRepository<Client, Integer> {
	List<Client> findById(int id);
	

}
