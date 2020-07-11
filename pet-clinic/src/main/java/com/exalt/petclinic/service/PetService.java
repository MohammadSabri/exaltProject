package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.DTO.PetDto;
import com.exalt.petclinic.DTO.PetUpdateDto;
import com.exalt.petclinic.model.Pet;

public interface PetService {
	PetDto create(PetUpdateDto petUpdateDto);
	PetDto get(int id);
	List<PetDto> getAll(int page, int limit);
	List<PetDto> getClientPets (int id,int page,int limit);
	Pet update(int id, PetUpdateDto pet);
	String delete (int id);
}
