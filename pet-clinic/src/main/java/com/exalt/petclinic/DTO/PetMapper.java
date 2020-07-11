package com.exalt.petclinic.DTO;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;

@Mapper
public interface PetMapper {
	@Mapping(target = "schedule",ignore = true)
	@Mapping(target = "id",ignore = true)
	@Mapping(target = "creationDate",ignore = true)
	@Mapping(target = "client",source = "clientId")
	Pet dtoToPetUpdate(PetUpdateDto petUpdateDto);
	PetDto petToDto (Pet pet);
	List<PetDto> petToDto (List<Pet> pets);
	
	
	default Client idToClient(int id) {
		Client client= new Client();
		client.setId(id);
		return client;
	}
}

