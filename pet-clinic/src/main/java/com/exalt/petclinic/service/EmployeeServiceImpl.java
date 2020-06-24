package com.exalt.petclinic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Employee.WorkingField;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static List<Employee> employeeArray = new ArrayList<Employee>(Arrays.asList(
			new Employee(1, "dia", "jam", "birzeit-uni street", 7000.0, "ScD", WorkingField.Owner, "23123213", 7,
					"1/4/2015", "123456789", "mofs@fdsf.com"),
			new Employee(2, "moh", "sa", "ramallah-safd street", 4220.0, "M.D", WorkingField.Worker, "3123524", 5,
					"23/4/2018", "123456789", "mofs@fdsf.com"),
			new Employee(3, "geha", "fadal", "nablus-hos street", 3700.0, "M.D", WorkingField.Worker, "6754646", 3,
					"3/2/2017", "123456789", "mofs@fdsf.com"),
			new Employee(4, "abd", "qar", "safad-safd street", 3000.0, " B.E", WorkingField.Admin, "3123524", 2,
					"23/4/2017", "123456789", "mofs@fdsf.com"),
			new Employee(5, "moh", "sa", "ramallah-safd street", 2700.0, "B.Res.Ec.", WorkingField.Admin, "3123524", 2,
					"23/4/2018", "123456789", "mofs@fdsf.com")));

	@Override
	public Employee getEmployee(int id) {
		return employeeArray.stream().filter(p -> p.getId() == id).findFirst().get();
	}

	@Override
	public List<Employee> getAllWorker(int page, int limit) {

		return employeeArray.stream().filter(p -> p.getWorkingField().equals(WorkingField.Worker))
				.skip((long) (page - 1) * limit).limit((long) limit).collect(Collectors.toList());

	}

	@Override
	public Employee creatWorker(Employee employee) {
		employeeArray.add(employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		for (Employee e : employeeArray) {
			if (e.getId() == id) {
				e.setFirstName(employee.getFirstName());
				e.setLastName(employee.getLastName());
				e.setHouseLocation(employee.getHouseLocation());
				e.setSalary(employee.getSalary());
				e.setCollegeDegree(employee.getCollegeDegree());
				e.setWorkingField(employee.getWorkingField());
				e.setPhoneNumber(employee.getPhoneNumber());
				e.setYearsOfExperience(employee.getYearsOfExperience());
				e.setEmail(employee.getEmail());
				return e;
			}
		}
		return null;
	}

	@Override
	public void deleteWorker(int id) {
		employeeArray.removeIf((p -> p.getId() == id));
	}

	@Override
	public List<Employee> getAllAdmin(int page, int limit) {
		return employeeArray.stream().filter(p -> p.getWorkingField().equals(WorkingField.Admin)).skip((long) page)
				.limit((long) limit).collect(Collectors.toList());
	}

	@Override
	public Employee creatAdmin(Employee employee) {
		employeeArray.add(employee);
		return employee;
	}

	@Override
	public void deleteAdmin(int id) {
		employeeArray.removeIf((p -> p.getId() == id));
	}

	@Override
	public List<Employee> getAllOwner(int page, int limit) {
		System.out.println("sda");
		return employeeArray.stream().filter(p -> p.getWorkingField().equals(WorkingField.Owner)).skip((long) page)
				.limit((long) limit).collect(Collectors.toList());
	}

	@Override
	public Employee creatOwner(Employee employee) {
		employeeArray.add(employee);
		return null;
	}

	@Override
	public void deleteOwner(int id) {
		employeeArray.removeIf((p -> p.getId() == id));
	}
}
