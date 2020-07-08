package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.DTO.ScheduleDto;
import com.exalt.petclinic.model.Schedule;

public interface ScheduleService {
	ScheduleDto create(ScheduleDto scheduleDto);
	Schedule update(int id, Schedule schedule);
	ScheduleDto get(int id);
	List<ScheduleDto> getAll(int page, int limit);
	String delete (int id);
}
