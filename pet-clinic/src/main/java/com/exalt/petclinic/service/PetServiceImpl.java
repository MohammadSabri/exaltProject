package com.exalt.petclinic.service;

import java.util.Calendar;
import java.util.Date;
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
		Pet pet = petMapper.updateDtoToPet(petUpdateDto);
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
	public List<PetDto> getClientPets(int id, int page, int limit) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		return petMapper.petToDto(petRepository.findAllPetsByClientIdNQ(id, (page - 1) * limit, limit));
	}

	@Override
	public PetUpdateDto update(int id, PetUpdateDto petUpdateDto) {
		if (id <= 0) {
		}
		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		if (petUpdateDto.getClientId() < 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED_IN_UPDATE);
		}
		Pet pet = petRepository.findById(id).get();
		Date date = pet.getCreationDate();
		if ((petUpdateDto.getClientId() < 0 || clientRepository.findClientExistNQ(petUpdateDto.getClientId()) == 0)) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		}
		
		pet = petMapper.updateDtoToPet(petUpdateDto);
		pet.setId(id);
		pet.setCreationDate(date);
		petRepository.save(pet);
		return petMapper.petToUpdateDto(pet);
		
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
