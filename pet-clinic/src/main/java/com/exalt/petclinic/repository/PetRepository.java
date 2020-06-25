package com.exalt.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.exalt.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {

	
}
