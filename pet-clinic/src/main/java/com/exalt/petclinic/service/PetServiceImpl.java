package com.exalt.petclinic.service;

import java.util.Calendar;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exalt.petclinic.DTO.PetDto;
import com.exalt.petclinic.DTO.PetMapper;
import com.exalt.petclinic.DTO.PetUpdateDto;
import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.repository.PetRepository;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	private PetRepository petRepository;

	@Autowired
	private ClientRepository clientRepository;

	private PetMapper petMapper = Mappers.getMapper(PetMapper.class);

	@Override
	public PetDto create(PetUpdateDto petUpdateDto) {

		if (petUpdateDto.getClientId() < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (clientRepository.findClientExistNQ(petUpdateDto.getClientId()) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);

		}
		Pet pet = petMapper.dtoToPetUpdate(petUpdateDto);
		pet.setCreationDate(Calendar.getInstance().getTime());
		petRepository.save(pet);
		pet.setId(petRepository.findPetIdNQ());
		return petMapper.petToDto(pet);
	}

	@Override
	public PetDto get(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (petRepository.findPetExistNQ(id) == 0) {

			throw new CommonException(ErrorEnum.PET_NOT_FOUND);

		} else {

			return petMapper.petToDto(petRepository.findById(id).get());
		}

	}

	@Override
	public List<PetDto> getAll(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}

		Pageable pageable = PageRequest.of((page - 1) * limit, limit);
		Page<Pet> pagedResult = petRepository.findAll(pageable);
		return petMapper.petToDto(pagedResult.toList());

	}

	@JsonPropertyOrder(alphabetic = true)
	@Override
	public List<PetDto> getClientPets(int id,int page,int limit) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		return petMapper.petToDto(petRepository.findAllPetsByClientId(id, (page-1)*limit, limit));
	}

	@Override
	public Pet update(int id, PetUpdateDto pet) {
		if (id <= 0) {
		}
		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		if (pet.getClientId() < 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED_IN_UPDATE);
		}
		Pet petTemp = petRepository.findById(id).get();
		if (pet.getClientId() > 0 && clientRepository.findClientExistNQ(pet.getClientId()) != 0) {
			Client client = new Client();
			client.setId(pet.getClientId());
			petTemp.setClient(client);
		}
		if (!(pet.getName().equals(null) && pet.getName().isEmpty())) {
			petTemp.setName(pet.getName());
		}
		if (pet.getAge() > 0) {
			petTemp.setAge(pet.getAge());
		}
		if (pet.getHeight() > 0.0) {
			petTemp.setHeight(pet.getHeight());
		}
		if (pet.getWeight() > 0.0) {
			petTemp.setWeight(pet.getWeight());

		}
		if (!(pet.getSpecies().equals(null) && pet.getSpecies().isEmpty())) {
			petTemp.setSpecies(pet.getSpecies());
		}
		petRepository.save(petTemp);
		return petTemp;

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

}
