package com.exalt.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.projection.PetProjection;
import com.exalt.petclinic.repository.PetRepository;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	private PetRepository petRepository;

	@Override
	public Pet create(Pet pet) {
		petRepository.save(pet);
		return pet;
	}

	@Override
	public Pet update(int id, Pet pet) {

		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		Pet petTemp = petRepository.findById(id).get();
		petTemp.setName(pet.getName());
		petTemp.setAge(pet.getAge());
		petTemp.setHeight(pet.getHeight());
		petTemp.setWeight(pet.getWeight());
		petTemp.setSpecies(pet.getSpecies());
		petRepository.save(petTemp);
		return petTemp;

	}

	@Override
	public Pet get(int id) {

		if (petRepository.findPetExistNQ(id) == 0) {

			throw new CommonException(ErrorEnum.PET_NOT_FOUND);

		} else {

			Pet petTemp = petRepository.findById(id).get();
			return petTemp;
		}

	}

	@Override
	public List<Pet> getAll(int page, int limit) {

		Pageable pageable = PageRequest.of(page, limit);
		Page<Pet> pagedResult = petRepository.findAll(pageable);
		return pagedResult.toList();

	}

	@JsonPropertyOrder(alphabetic = true)
	@Override
	public List<PetProjection> getClientPets(int id) {

		return petRepository.findPetsNQ(id);
	}

	@Override
	public String delete(int id) {
		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		petRepository.deleteById(id);
		return "Pet deleted successfully ";
	}

}
