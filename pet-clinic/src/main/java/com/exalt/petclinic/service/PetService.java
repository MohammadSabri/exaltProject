package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.DTO.PetDTO;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.projection.PetProjection;

public interface PetService {
	Pet create(Pet pet);
	Pet update(int id, Pet pet);
	Pet get(int id);
	List<Pet> getAll(int page, int limit);
	List<PetProjection> getClientPets (int id);
	String delete (int id);
	List<PetDTO> getAllDTO();
}
