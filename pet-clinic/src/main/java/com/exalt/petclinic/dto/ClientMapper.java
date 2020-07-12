package com.exalt.petclinic.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.exalt.petclinic.model.Client;

@Mapper
public interface ClientMapper {
	ClientDto clientToDto(Client client);

	List<ClientDto> clientToDto(List<Client> clients);

	@Mapping(target = "pets", ignore = true)
	Client dtoToClient(ClientDto clientDto);

	List<Client> dtoToClient(List<ClientDto> clientDtos);

	@Mapping(target = "pets", ignore = true)
	@Mapping(target = "creationDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	Client updateDtoToClient(ClientUpdateDto clientUpdateDto);

	ClientUpdateDto clientToUpdateDto(Client client);
}
