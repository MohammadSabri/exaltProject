package com.exalt.petclinic.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.service.FileService;
@RestController
public class FileController {
@Autowired
FileService	fileService;

@PostMapping(path = "/api/v1/file/{fileName}")
public String creatFile( @PathVariable String fileName ) {

	return fileService.create(fileName);
}
@GetMapping(path = "/api/v1/file/{fileName}")
public String getFile( @PathVariable String fileName ) {

	return fileService.get(fileName);
}

@PutMapping(path = "/api/v1/file/{fileName}")
public String updatePet(@RequestBody String data, @PathVariable String fileName) {
	return fileService.update(data, fileName);
}
}