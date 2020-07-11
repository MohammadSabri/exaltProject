package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.projection.PetProjection;

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

	@Query(value = "select count(*) from pet where id=:id ", nativeQuery = true)
	int findPetExistNQ(@Param("id") int id);

	@Query(value = "select p.id,p.name,p.age,p.species,p.height,p.weight,p.creation_date as creationDate from client c join pet p where c.id =p.client_id and c.id=:id ", nativeQuery = true)
	List<PetProjection> findPetsNQ(@Param("id") int id);

	List<Pet> findByClientId(int id);
	@Query(value = "select * from  pet where client_id=:id limit:limit offset:offset ", nativeQuery = true)
	List<Pet> findAllPetsByClientId(@Param("id") int id,@Param("offset") int offset, @Param("limit") int limit);

	@Query(value = "select max(id) from pet ", nativeQuery = true)
	int findPetIdNQ();
	
	
	
	
	
}
