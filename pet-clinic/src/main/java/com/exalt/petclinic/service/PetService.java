package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.dto.PetDto;
import com.exalt.petclinic.dto.PetUpdateDto;

public interface PetService {
	PetDto create(PetUpdateDto petUpdateDto);
	PetDto get(int id);
	List<PetDto> getAll(int page, int limit);
	List<PetDto> getClientPets (int id,int page,int limit);
	PetUpdateDto update(int id, PetUpdateDto petUpdateDto);
	String delete (int id);
}
