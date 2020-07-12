package com.exalt.petclinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.DTO.PetDto;
import com.exalt.petclinic.DTO.PetUpdateDto;
import com.exalt.petclinic.repository.PetRepository;
import com.exalt.petclinic.service.PetService;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
public class PetController {

	@Autowired
	private PetService petService;

	@Autowired
	PetRepository petRepository;

	@PostMapping(path = "/api/v1/pets", consumes = "application/json", produces = "application/json")

	public PetDto addPet(@Valid @RequestBody PetUpdateDto petUpdateDto) {
		return petService.create(petUpdateDto);
	}

	@ApiOperation(value = "Find all pets", notes = "find all pets with limitation of page and limit parameters")
	@GetMapping(path = "/api/v1/pets", params = { "page", "limit" })
	public List<PetDto> getPets(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (petService.getAll(page, limit));
	}

	@GetMapping("/api/v1/pets/{id}")

	public PetDto getOnePet(@PathVariable int id) {

		return petService.get(id);
	}

	@GetMapping(path = "/api/v1/pets/client/{id}", params = { "page", "limit" })

	public List<PetDto> getClientPets(@PathVariable int id, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		return (petService.getClientPets(id, page, limit));
	}

	@PutMapping(path = "/api/v1/pets/{id}", consumes = "application/json", produces = "application/json")
	public PetUpdateDto updatePet(@Valid @RequestBody PetUpdateDto petUpdateDto, @PathVariable int id) {
		return petService.update(id, petUpdateDto);
	}

	@DeleteMapping("/api/v1/pets/{id}")
	public String deletePet(@PathVariable int id) {

		return petService.delete(id);
	}

}
