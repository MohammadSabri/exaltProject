package com.exalt.petclinic.DTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.model.Schedule;

@Component
public class ScheduleMapper {
	public Schedule dtoToSchedule(ScheduleDto scheduleDto) {
	Pet pet =new Pet();
	Employee employee =new Employee();
	employee.setId(scheduleDto.getEmployeeId());
	Schedule schedule =new Schedule();
	schedule.setDate(scheduleDto.getDate());
	schedule.setPrice(scheduleDto.getPrice());
	schedule.setMedicalDescribtion(scheduleDto.getMedicalDescribtion());
	schedule.setProblemDescribtion(scheduleDto.getProblemDescribtion());
	schedule.setEmployee(employee);
	schedule.setPet(pet);
	return schedule;
	}
	public List<Schedule> dtoToSchedule(List<ScheduleDto> scheduleDto) {
		return scheduleDto.stream().map(d -> dtoToSchedule(d)).collect(Collectors.toList());
	}
	
	public ScheduleDto scheduleToDto (Schedule schedule) {
		
		ScheduleDto scheduleDto=new ScheduleDto();
		scheduleDto.setId(schedule.getId());
		scheduleDto.setDate(schedule.getDate());
		scheduleDto.setPrice(schedule.getPrice());
		scheduleDto.setMedicalDescribtion(schedule.getMedicalDescribtion());
		scheduleDto.setProblemDescribtion(schedule.getProblemDescribtion());
		scheduleDto.setEmployeeId(schedule.getEmployee().getId());
		scheduleDto.setPetId(schedule.getPet().getId());
		return scheduleDto;
	}
	public List<ScheduleDto> scheduleToDto(List<Schedule> schedules) {
		return schedules.stream().map(d -> scheduleToDto(d)).collect(Collectors.toList());

	}
	
}
