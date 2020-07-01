package com.exalt.petclinic.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.repository.PetRepository;
import com.exalt.petclinic.service.PetService;

@RestController
@Validated
public class PetController {

	@Autowired
	private PetService petService;
	
	@Autowired
	PetRepository petRepository;

	@GetMapping(path = "/api/v1/pets", params = { "page", "limit" })
	public List<Pet> getPets(@RequestParam("page") @Min(value = 0, message = "page number must by >0") int page,
			@RequestParam("limit") @Min(value = 1, message = "limit must be >1(must return some value)") @Max(value = 1000, message = "The max is 1000") int limit) {

		return (petService.getAll(page, limit));
	}

	@GetMapping("/api/v1/pets/{id}")

	public Pet getOnePet(@PathVariable @Min(value = 1, message = "there is no pet id with value <1") int id) {
		
		return petRepository.findById(id).get();
	//	return (petService.get(id));
	}

	@GetMapping("/api/v1/pets/client/{id}")

	public List<Pet> getClientPets(
			@PathVariable @Min(value = 1, message = "there is no client id with value <1") int id) {

		return (petService.getClientPets(id));
	}

	@PostMapping(path = "/api/v1/pets", consumes = "application/json", produces = "application/json")

	public Pet addPet(@Valid @RequestBody Pet pet) {
		return petService.create(pet);
	}

	@PutMapping(path = "/api/v1/pets/{id}", consumes = "application/json", produces = "application/json")
	public Pet updatePet(@Valid @RequestBody Pet pet, @PathVariable @Min(1) int id) {
		return petService.update(id, pet);
	}

	@DeleteMapping("/api/v1/pets/{id}")
	public void deletePet(@PathVariable @Min(1) int id) {

		petService.delete(id);
	}

}
