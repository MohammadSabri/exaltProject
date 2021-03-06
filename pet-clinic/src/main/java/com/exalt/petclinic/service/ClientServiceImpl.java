package com.exalt.petclinic.service;

import java.util.Calendar;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.dto.ClientDto;
import com.exalt.petclinic.dto.ClientMapper;
import com.exalt.petclinic.dto.ClientUpdateDto;
import com.exalt.petclinic.dto.PageDto;
import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

	@Override
	@Transactional
	public Client create(ClientUpdateDto clientUpdateDto) {

		if (clientUpdateDto.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}

		if (clientRepository.findEmailExistNQ(clientUpdateDto.getEmail()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if (clientRepository.findNumberExistNQ(clientUpdateDto.getPhoneNumber()) != 0)
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		Client client = clientMapper.updateDtoToClient(clientUpdateDto);
		client.setCreationDate(Calendar.getInstance().getTime());
		return clientRepository.save(client);

	}

	@Override
	@Transactional(readOnly = true)
	public ClientDto get(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (clientRepository.findClientExistNQ(id) == 0)
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		else
			return clientMapper.clientToDto(clientRepository.findById(id).get());

	}

	@Override
	@Transactional(readOnly = true)
	public PageDto<ClientDto> getAll(int page, int limit) {

		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		Pageable pageable = PageRequest.of((page - 1) , limit);
		Page<Client> pagedResult = clientRepository.findAll(pageable);
		System.out.println("******************************");
		System.out.println(pagedResult.getNumberOfElements());

		for (Client c : pagedResult.getContent()) {
			System.out.println(c.getPhoneNumber());
		}
		System.out.println("*******************************");
		PageDto<ClientDto> pageDto = new PageDto<>(clientMapper.clientToDto(pagedResult.getContent()), pagedResult.getTotalElements(),
				pagedResult.getNumberOfElements());
		return pageDto;

	}

	@Override
	@Transactional
	public ClientUpdateDto update(int id, ClientUpdateDto clientUpdateDto) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (clientRepository.findClientExistNQ(id) == 0)
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);

		Client client = clientRepository.findById(id).get();

		if (clientUpdateDto.getPhoneNumber().length() != 10)
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);

		if ((clientRepository.findEmailExistNQ(clientUpdateDto.getEmail()) != 0)
				&& !(clientUpdateDto.getEmail().equals(client.getEmail()))) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if ((clientRepository.findNumberExistNQ(clientUpdateDto.getPhoneNumber()) != 0)
				&& !(clientUpdateDto.getPhoneNumber().equals(client.getPhoneNumber())))
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		client.setEmail(clientUpdateDto.getEmail());
		client.setFirstName(clientUpdateDto.getFirstName());
		client.setLastName(clientUpdateDto.getLastName());
		client.setPhoneNumber(clientUpdateDto.getPhoneNumber());
		client.setPassword(clientUpdateDto.getPassword());
		clientRepository.save(client);

		return clientMapper.clientToUpdateDto(client);
	}

	@Override
	@Transactional
	public String delete(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (clientRepository.findClientExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			clientRepository.deleteById(id);
			if (clientRepository.findClientExistNQ(id) == 0) {
				return "Client Deleted sucsessfuly";
			} else {
				return "Client Not Deleted";
			}
		}
	}

}
