package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.dto.EmployeeDto;
import com.exalt.petclinic.dto.EmployeeUpdateDto;

public interface EmployeeService {
	EmployeeDto getEmployee(int id);

	List<EmployeeDto> getAllAdmin(int page, int limit);

	List<EmployeeDto> getAllWorker(int page, int limit);

	List<EmployeeDto> getAllOwner(int page, int limit);

	EmployeeDto creatEmployee(EmployeeUpdateDto employeeUpdateDto);

	String deleteEmployee(int id);

	EmployeeDto updateEmployee(int id, EmployeeUpdateDto employeeUpdateDto);

}
