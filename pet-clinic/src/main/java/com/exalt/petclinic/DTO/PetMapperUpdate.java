package com.exalt.petclinic.DTO;

import org.springframework.stereotype.Component;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;

@Component
public class PetMapperUpdate {
	public PetDtoUpdate petToPetDto(Pet pet) {
		PetDtoUpdate petDtoUpdate = new PetDtoUpdate();
		petDtoUpdate.setName(pet.getName());
		petDtoUpdate.setAge(pet.getAge());
		petDtoUpdate.setClientId(pet.getClient().getId());
		petDtoUpdate.setHeight(pet.getHeight());
		petDtoUpdate.setSpecies(pet.getSpecies());
		petDtoUpdate.setWeight(pet.getWeight());

		return petDtoUpdate;
	}

	public Pet petDtoToPet(PetDtoUpdate petDtoUpdate, Pet pet) {
		Client client = new Client();
		client.setId(petDtoUpdate.getClientId());
		return pet;
	}

}
