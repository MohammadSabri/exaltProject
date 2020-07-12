package com.exalt.petclinic.DTO;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.model.Schedule;

@Mapper
public interface ScheduleMapper {

	@Mapping(target = "employee", source = "scheduleUpdateDto")
	@Mapping(target = "pet", source = "scheduleUpdateDto")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationDate", ignore = true)
	Schedule updateDtoToSchedule(ScheduleUpdateDto scheduleUpdateDto);

	@Mapping(target = "employeeId", source = "employee")
	@Mapping(target = "petId", source = "pet")
	ScheduleUpdateDto scheduleToUpdateDto(Schedule schedule);

	@Mapping(target = "employeeId", source = "employee")
	@Mapping(target = "petId", source = "pet")
	ScheduleDto scheduleToDto(Schedule schedule);

	List<ScheduleDto> scheduleToDto(List<Schedule> schedules);

	default Employee toEmployee(ScheduleDto scheduleDto) {
		Employee employee = new Employee();
		employee.setId(scheduleDto.getEmployeeId());
		return employee;
	}

	default Pet toPet(ScheduleDto scheduleDto) {
		Pet pet = new Pet();
		pet.setId(scheduleDto.getPetId());
		return pet;
	}

	default Employee toEmployee(ScheduleUpdateDto scheduleUpdateDto) {
		Employee employee = new Employee();
		employee.setId(scheduleUpdateDto.getEmployeeId());
		return employee;
	}

	default Pet toPet(ScheduleUpdateDto scheduleUpdateDto) {
		Pet pet = new Pet();
		pet.setId(scheduleUpdateDto.getPetId());
		return pet;
	}

	default int employeeToId(Employee employee) {
		return employee.getId();
	}

	default int petToId(Pet pet) {
		return pet.getId();
	}
}
