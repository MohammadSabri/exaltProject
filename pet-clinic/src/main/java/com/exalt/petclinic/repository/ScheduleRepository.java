package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exalt.petclinic.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
	
	Schedule findByEmployeeId(int  employeeId);

}
