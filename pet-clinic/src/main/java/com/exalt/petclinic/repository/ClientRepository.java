package com.exalt.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exalt.petclinic.model.Client;


public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query(value = "select count(*) from client where email=:email ",nativeQuery = true)
	int findEmailExistNQ(@Param("email") String email);
	
	@Query(value = "select count(*) from client where phone_number=:phoneNumber ",nativeQuery = true)
	int findNumberExistNQ(@Param("phoneNumber") String phoneNumber);
	
	@Query(value = "select count(*) from client where id=:id ",nativeQuery = true)
	int findClientExistNQ(@Param("id") int  id);
	
	
}
