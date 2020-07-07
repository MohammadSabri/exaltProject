package com.exalt.petclinic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Employee.WorkingField;
import com.exalt.petclinic.model.Role;
import com.exalt.petclinic.model.Role.RoleEnum;
import com.exalt.petclinic.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeTest {
	@Autowired
	EmployeeRepository employeeRepository;
	@Test
	void testCreat() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2002, 1, 2, 2, 13);
		Date date = calendar.getTime();
		Employee employee =new Employee();
		employee.setFirstName("Wesam");
		employee.setLastName("Mtour");
		employee.setCollegeDegree("prof");
		employee.setHouseLocation("J");
		employee.setEmail("Wesam@test.com");
		employee.setPhoneNumber("4030220040");
		employee.setSalary(6000.0);
		employee.setWorkingField(WorkingField.Owner);
		employee.setPassword("21342223424342");
		employee.setYearsOfExperience(3);
		employee.setCreationDate(date);
		
		List<Employee> employees =new ArrayList<Employee>();
		List<Role> roles =new ArrayList<Role>();

		Role role =new Role();
		role.setRole(RoleEnum.Admin);
		Role role2 =new Role();
		role2.setRole(RoleEnum.Owner);
		role.setId(2);
		role2.setId(1);
		
		employees.add(employee);
		roles.add(role);
		roles.add(role2);
		
		employee.setRoles(roles);
		role.setEmployees(employees);
		role2.setEmployees(employees);
		

		employeeRepository.save(employee);
		
		
	}
	
	@Test
	void deleteTest()
	{
		employeeRepository.deleteById(15);
	}

}
