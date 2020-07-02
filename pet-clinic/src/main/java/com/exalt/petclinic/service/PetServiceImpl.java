package com.exalt.petclinic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	PetRepository petRepository;

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
		return petRepository.findAll();
		// return PetsArray.stream().skip((long) (page - 1) * limit).limit((long)
		// limit).collect(Collectors.toList());
	}

	@Override
	public List<Pet> getClientPets(int id) {
		// return PetsArray.stream().filter(p -> p.getClientId() ==
		// id).collect(Collectors.toList());
		return null;
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
