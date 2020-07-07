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

import com.exalt.petclinic.DTO.PetDTO;
import com.exalt.petclinic.DTO.PetDtoUpdate;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.projection.PetProjection;
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
	public List<Pet> getPets(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		return (petService.getAll(page, limit));
	}
	
	@GetMapping(path = "/api/v1/petsDTO")
	public List<PetDTO> getPetsDTO() {

		return petService.getAllDTO();
	}

	@GetMapping("/api/v1/pets/{id}")

	public Pet getOnePet(@PathVariable int id) {

		return petService.get(id);
	}

	@GetMapping("/api/v1/pets/client/{id}")

	public List<PetProjection> getClientPets(@PathVariable int id) {

		return (petService.getClientPets(id));
	}

	@PostMapping(path = "/api/v1/pets", consumes = "application/json", produces = "application/json")

	public Pet addPet(@Valid @RequestBody Pet pet) {
		return petService.create(pet);
	}

	@PutMapping(path = "/api/v1/pets/{id}", consumes = "application/json", produces = "application/json")
	public Pet updatePet(@Valid @RequestBody PetDtoUpdate pet, @PathVariable int id) {
		return petService.update(id, pet);
	}

	@DeleteMapping("/api/v1/pets/{id}")
	public String deletePet(@PathVariable int id) {

		return petService.delete(id);
	}

}
