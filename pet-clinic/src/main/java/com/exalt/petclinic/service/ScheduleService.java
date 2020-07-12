package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.DTO.ScheduleDto;
import com.exalt.petclinic.DTO.ScheduleUpdateDto;

public interface ScheduleService {
	ScheduleDto create(ScheduleUpdateDto scheduleUpdateDto);
	ScheduleDto get(int id);
	List<ScheduleDto> getAll(int page, int limit);
	ScheduleDto update(int id, ScheduleUpdateDto scheduleUpdateDto);
	String delete (int id);
	List<ScheduleDto> getAllByWorkerId(int id,int page, int limit);
	List<ScheduleDto> getAllByPetId(int id,int page, int limit);
	List<ScheduleDto> getAllByClientId(int id,int page, int limit);

}
