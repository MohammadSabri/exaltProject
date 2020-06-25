package com.exalt.petclinic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Client;

@Service
public class ClientServiceImpl implements ClientService {
	//public static final Pattern VALID_EMAIL_ADDRESS_REGEX =   Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private final static List<Client> clientArray = new ArrayList<>(
			Arrays.asList(new Client(1, "moh", "sab", "0592573952", "ewwew@fsdfs.com",new Date(2020, 2, 4), "123456789"),
					new Client(2, "fda", "sgab", "0592586491", "gdfg@fsdfs.com", new Date(2020, 2, 4), "123456789"),
					new Client(3, "fadi", "hamad", "0563111417", "ewwew@fsdfs.com", new Date(2020, 2, 4), "123456789")));


	@Override
	public Client create(Client client) {
		if (client.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}

		//Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(client.getEmail());
		//if (!matcher.find())
		//	throw new CommonException(ErrorEnum.EMAIL_INVALID);
		for (Client c : clientArray) {
			if (c.getId() == client.getId() || c.getEmail() == client.getEmail()
					|| c.getPhoneNumber() == client.getPhoneNumber())
				throw new CommonException(ErrorEnum.EXIST_CLIENT);
		}

		clientArray.add(client);
		return client;

	}

	@Override
	public Client update(int id, Client client) {
		if (client.getPhoneNumber().length() != 10)
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		
	//	Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(client.getEmail());
		//if (!matcher.find())
		//	throw new CommonException(ErrorEnum.EMAIL_INVALID);
		for (Client c : clientArray) {
			if (c.getEmail() == client.getEmail() || c.getPhoneNumber() == client.getPhoneNumber())
				throw new CommonException(ErrorEnum.EXIST_CLIENT);
		}

		for (Client c : clientArray) {
			if (c.getId() == id) {

				c.setEmail(client.getEmail());
				c.setFirstName(client.getFirstName());
				c.setLastName(client.getLastName());
				c.setPhoneNumber(client.getPhoneNumber());
				c.setPassword(client.getPassword());
				return c;
			}
		}
		return null;
	}

	@Override
	public Client get(int id) {
		
		return clientArray.stream().filter(c -> c.getId() == id).findFirst().orElseThrow(() -> new CommonException(ErrorEnum.USER_NOT_FOUND));
	}

	@Override
	public List<Client> getAll(int page, int limit) {
		
		return clientArray.stream().skip((long) (page-1)*limit).limit((long) limit).collect(Collectors.toList());
	}

	@Override
	public void delete(int id) {
	
		if(!clientArray.removeIf(c -> c.getId() == id))
		throw new CommonException(ErrorEnum.USER_NOT_FOUND);
	}

}
