package com.exalt.petclinic.dto;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Role;

@Mapper
public interface EmployeeMapper {
	EmployeeDto employeeToDto(Employee employee);

	List<EmployeeDto> employeeToDto(List<Employee> employees);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "schedule", ignore = true)
	@Mapping(target = "roles", ignore = true)
	@Mapping(target = "creationDate", ignore = true)

	Employee updateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationDate", ignore = true)
	EmployeeDto updateDtoToDto(EmployeeUpdateDto employeeUpdateDto);

	default List<Integer> fromRolesToInt(List<Role> roles) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Role r : roles) {
			ids.add(r.getId());
		}
		return ids;
	}

}
