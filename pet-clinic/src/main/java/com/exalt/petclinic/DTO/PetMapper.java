package com.exalt.petclinic.DTO;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.exalt.petclinic.model.Pet;
@Component
public class PetMapper {
	public PetDTO petToPetDto(Pet pet) {
		ModelMapper mapper = new ModelMapper();
		PetDTO petDto = mapper.map(pet, PetDTO.class);
		return petDto;
	}

	public List<PetDTO> petToPetDto(List<Pet> pet) {
		return pet.stream().map(d -> petToPetDto(d)).collect(Collectors.toList());

	}

	public Pet petDtoToPet(PetDTO petDTO) {
		ModelMapper mapper = new ModelMapper();
		Pet pet = mapper.map(petDTO, Pet.class);
		return pet;
	}

	public List<Pet> petDtoToPet(List<PetDTO> petDTO) {
		return petDTO.stream().map(d -> petDtoToPet(d)).collect(Collectors.toList());
	}
}
