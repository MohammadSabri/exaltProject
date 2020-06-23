package com.exalt.petclinic.service;

import java.util.List;

import com.exalt.petclinic.model.Employee;

public interface EmployeeService {
	Employee getEmployee(int id);
	List<Employee> getAllWorker(int page, int limit);
	Employee creatWorker(Employee employee);
	Employee updateEmployee(int id, Employee employee);
	void deleteWorker(int id);
	List<Employee> getAllAdmin(int page, int limit);
	Employee creatAdmin(Employee employee);
	void deleteAdmin(int id);
	List<Employee> getAllOwner(int page, int limit);
	Employee creatOwner(Employee employee);
	void deleteOwner(int id);

}
