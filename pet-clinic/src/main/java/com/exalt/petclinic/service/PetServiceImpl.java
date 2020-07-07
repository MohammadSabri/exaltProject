package com.exalt.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exalt.petclinic.DTO.PetDTO;
import com.exalt.petclinic.DTO.PetDtoUpdate;
import com.exalt.petclinic.DTO.PetMapper;
import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.projection.PetProjection;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.repository.PetRepository;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private PetMapper petMapper;
	@Autowired
	private ClientRepository clientRepository;


	@Override
	public Pet create(Pet pet) {
		
		petRepository.save(pet);
		return pet;
	}

	@Override
	public Pet update(int id, PetDtoUpdate pet) {
		if (id <= 0) {
		}
		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		if (pet.getClientId()<0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED_IN_UPDATE);
		}
		Pet petTemp = petRepository.findById(id).get();
		if (pet.getClientId()>0 && clientRepository.findClientExistNQ(pet.getClientId())!=0) {
			Client client =new Client();
			client.setId(pet.getClientId());
			petTemp.setClient(client);
		}
		if(!(pet.getName().equals(null) && pet.getName().isEmpty())) {
			petTemp.setName(pet.getName());
		}
		if (pet.getAge()>0) {
			petTemp.setAge(pet.getAge());	
		}
		if (pet.getHeight()>0.0) {
		petTemp.setHeight(pet.getHeight());
		}
		if (pet.getWeight()>0.0) {
			petTemp.setWeight(pet.getWeight());
				
		}
		if (!(pet.getSpecies().equals(null)&& pet.getSpecies().isEmpty() )) {
			petTemp.setSpecies(pet.getSpecies());
		}
		petRepository.save(petTemp);
		return petTemp;

	}

	@Override
	public Pet get(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (petRepository.findPetExistNQ(id) == 0) {

			throw new CommonException(ErrorEnum.PET_NOT_FOUND);

		} else {

			Pet petTemp = petRepository.findById(id).get();
			return petTemp;
		}

	}

	@Override
	public List<Pet> getAll(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}

		Pageable pageable = PageRequest.of((page - 1) * limit, limit);
		Page<Pet> pagedResult = petRepository.findAll(pageable);
		return pagedResult.toList();

	}

	@JsonPropertyOrder(alphabetic = true)
	@Override
	public List<PetProjection> getClientPets(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		return petRepository.findPetsNQ(id);
	}

	@Override
	public String delete(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		petRepository.deleteById(id);
		return "Pet deleted successfully ";
	}

	@Override
	public List<PetDTO> getAllDTO() {

		return petMapper.petToPetDto(petRepository.findAll());

	}

}
