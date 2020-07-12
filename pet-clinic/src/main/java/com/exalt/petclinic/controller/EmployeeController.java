package com.exalt.petclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.service.EmployeeService;

//TODO Convert to Dto
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = { "/api/v1/owner", "/api/v1/admin/owner" }, params = { "page", "limit" })
	public List<Employee> getAllOwner(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (employeeService.getAllOwner(page, limit));
	}

	@GetMapping(path = { "/api/v1/owner/admin", "/api/v1/admin" }, params = { "page", "limit" })
	public List<Employee> getAllAdmin(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (employeeService.getAllAdmin(page, limit));
	}

	@GetMapping(path = { "/api/v1/owner/worker", "/api/v1/admin/worker" }, params = { "page", "limit" })
	public List<Employee> getAllWorker(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (employeeService.getAllWorker(page, limit));
	}

	@GetMapping(path = { "/api/v1/owner/employee/{id}", "/api/v1/admin/employee/{id}" })

	public Employee getEmployee(@PathVariable int id) {

		return employeeService.getEmployee(id);
	}

	// ---------------------------end of get

	@PostMapping(path = { "/api/v1/owner/worker",
			"/api/v1/admin/worker" }, consumes = "application/json", produces = "application/json")

	public Employee creatWorker(@RequestBody Employee employee) {
		return employeeService.creatWorker(employee);
	}

	@PostMapping(path = "/api/v1/owner/admin", consumes = "application/json", produces = "application/json")

	public Employee creatAdmin(@RequestBody Employee employee) {
		return employeeService.creatAdmin(employee);
	}

	@PostMapping(path = "/api/v1/owner", consumes = "application/json", produces = "application/json")

	public Employee creatOwner(@RequestBody Employee employee) {
		return employeeService.creatOwner(employee);
	}

	// --------------------------------end of post

	@PutMapping(path = { "/api/v1/owner/admin/{id}",
			"/api/v1/owner/worker/{id}" }, consumes = "application/json", produces = "application/json")
	public Employee updateByOwner(@RequestBody Employee employee, @PathVariable int id) {
		return employeeService.updateEmployee(id, employee);
	}

	@PutMapping(path = { "/api/v1/admin/worker/{id}" }, consumes = "application/json", produces = "application/json")
	public Employee updateByAdmin(@RequestBody Employee employee, @PathVariable int id) {
		return employeeService.updateEmployee(id, employee);
	}

	// ----------------------------------
	@DeleteMapping("/api/v1/owner/admin/{id}")
	public void deleteAdmin(@PathVariable int id) {

		employeeService.deleteAdmin(id);
	}

	@DeleteMapping(path = { "/api/v1/owner/worker/{id}", "/api/v1/admin/worker/{id}" })
	public void deleteWorker(@PathVariable int id) {

		employeeService.deleteWorker(id);
	}

}
