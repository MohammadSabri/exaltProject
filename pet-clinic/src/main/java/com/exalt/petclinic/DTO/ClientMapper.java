package com.exalt.petclinic.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.exalt.petclinic.model.Client;

@Mapper
public interface ClientMapper {
ClientDto clientToDto (Client client) ;
@Mapping(target = "pets",ignore = true)
Client dtoToClient (ClientDto clientDto);

}
