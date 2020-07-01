package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.exalt.petclinic.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
	Client findById(int id);
	@Query(value = "select email,password from client",nativeQuery = true)
	List<Object> findByIdNQ(int id);

}
