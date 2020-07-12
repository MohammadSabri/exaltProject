package com.exalt.petclinic.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;

@Mapper
public interface PetMapper {
	@Mapping(target = "schedule", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationDate", ignore = true)
	@Mapping(target = "client", source = "clientId")
	Pet updateDtoToPet(PetUpdateDto petUpdateDto);

	PetDto petToDto(Pet pet);

	List<PetDto> petToDto(List<Pet> pets);

	@Mapping(target = "clientId", source = "client")
	PetUpdateDto petToUpdateDto(Pet pet);

	default Client idToClient(int id) {
		Client client = new Client();
		client.setId(id);
		return client;
	}

	default int clientToId(Client client) {

		return client.getId();
	}

}
