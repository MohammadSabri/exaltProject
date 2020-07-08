package com.exalt.petclinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.DTO.ScheduleDto;
import com.exalt.petclinic.model.Schedule;
import com.exalt.petclinic.service.ScheduleService;

@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	@GetMapping(path = "/api/v1/schedule", params = { "page", "limit" })
		public List<ScheduleDto> getAll ( @RequestParam("page") int page,
		@RequestParam("limit") int limit)
		{
			return scheduleService.getAll(page, limit);
		
		}
	
	@GetMapping(path = "/api/v1/schedule/{id}", produces = "application/json")
	public ScheduleDto get(@PathVariable int id) {

		return (scheduleService.get(id));
	}
	
	@PostMapping(path = "/api/v1/schedule",consumes = "application/json", produces = "application/json")
	public ScheduleDto addClient(@Valid @RequestBody ScheduleDto scheduleDto) {

		return scheduleService.create(scheduleDto);
	}
	@DeleteMapping("/api/v1/schedule/{id}")
	public String deleteClient(@PathVariable int id) {

		return scheduleService.delete(id);
	}
}
