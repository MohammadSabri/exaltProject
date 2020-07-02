package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exalt.petclinic.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	// find by id
	
	List<Pet> findByName(String name);

	List<Pet> findByNameAndAge(String name, int age);

	List<Pet> findByAgeGreaterThan(int age);


	List<Pet> findByWeightBetween(double min, double max);

	@Query("from Pet where name=:name")
	List<Pet> findAllPet(@Param("name") String name);

	
	@Query(value = "select * from pet where name=:name", nativeQuery = true)
	List<Pet> findAllPetNQ(@Param("name") String name);
	
	@Query(value = "select count(*) from pet where id=:id ",nativeQuery = true)
	int findPetExistNQ(@Param("id") int  id);

}
