package com.exalt.petclinic.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client create(Client client) {

		client.setPets(null);

		if (client.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}

		if (clientRepository.findEmailExistNQ(client.getEmail()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if (clientRepository.findNumberExistNQ(client.getPhoneNumber()) != 0)
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		clientRepository.save(client);

		return client;

	}

	@Override
	public Client update(int id, Client client) {

		if (clientRepository.findClientExistNQ(id) == 0)
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);

		Client clientTemp = clientRepository.findById(id).get();

		if (client.getPhoneNumber().length() != 10)
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);

		if ((clientRepository.findEmailExistNQ(client.getEmail()) != 0)
				&& !(client.getEmail().equals(clientTemp.getEmail()))) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if ((clientRepository.findNumberExistNQ(client.getPhoneNumber()) != 0)
				&& !(client.getPhoneNumber().equals(clientTemp.getPhoneNumber())))
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		clientTemp.setEmail(client.getEmail());
		clientTemp.setFirstName(client.getFirstName());
		clientTemp.setLastName(client.getLastName());
		clientTemp.setPhoneNumber(client.getPhoneNumber());
		clientRepository.save(clientTemp);

		return clientTemp;
	}

	@Override
	public Client get(int id) {

		if (clientRepository.findClientExistNQ(id) == 0)
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		else
			return clientRepository.findById(id).get();

	}

	@Override
	public List<Client> getAll(int page, int limit) {

		return clientRepository.findAll();

	}

	@Override
	public String delete(int id) {
		if (clientRepository.findClientExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			clientRepository.deleteById(id);
			return "Deleted sucsessfuly";
		}
	}

}
