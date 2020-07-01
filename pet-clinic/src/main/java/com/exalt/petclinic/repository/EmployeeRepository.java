package com.exalt.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.exalt.petclinic.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
