package com.exalt.petclinic.controller;

import java.util.List;

import javax.validation.Valid;

import com.exalt.petclinic.config.AuthorizationServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.dto.EmployeeDto;
import com.exalt.petclinic.dto.EmployeeUpdateDto;
import com.exalt.petclinic.service.EmployeeService;

//TODO Convert to DTO
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = { "/api/v1/owners", "/api/v1/admin/owners" }, params = { "page", "limit" })
	public List<EmployeeDto> getAllOwner(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (employeeService.getAllOwner(page, limit));
	}

	@GetMapping(path = { "/api/v1/owner/admins", "/api/v1/admins" }, params = { "page", "limit" })
	public List<EmployeeDto> getAllAdmin(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (employeeService.getAllAdmin(page, limit));
	}

	@GetMapping(path = { "/api/v1/owner/workers", "/api/v1/admin/workers", "/api/v1/workers" }, params = { "page",
			"limit" })
	public List<EmployeeDto> getAllWorker(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (employeeService.getAllWorker(page, limit));
	}

	@GetMapping(path = { "/api/v1/employee/{id}" })

	public EmployeeDto getEmployee(@PathVariable int id) {

		return employeeService.getEmployee(id);
	}

	@PostMapping(path = { "/api/v1/employee" })

	public EmployeeDto creatEmployee(@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto) {
		return employeeService.creatEmployee(employeeUpdateDto);
	}

	@PutMapping(path = { "/api/v1/employee/{id}" }, consumes = "application/json", produces = "application/json")
	public EmployeeDto updateEmployee(@RequestBody EmployeeUpdateDto employeeUpdateDto, @PathVariable int id) {
		return employeeService.updateEmployee(id, employeeUpdateDto);
	}

	@DeleteMapping("/api/v1/employee{id}")
	public void deleteEmployee(@PathVariable int id) {

		employeeService.deleteEmployee(id);
	}

}
