package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exalt.petclinic.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query(value = "select count(*) from employee where email=:email ",nativeQuery = true)
	int findEmailExistNQ(@Param("email") String email);
	
	@Query(value = "select count(*) from employee where phone_number=:phoneNumber ",nativeQuery = true)
	int findNumberExistNQ(@Param("phoneNumber") String phoneNumber);
	
	@Query(value = "select count(*) from employee where id=:id ",nativeQuery = true)
	int findEmployeeExistNQ(@Param("id") int  id);
	
	@Query(value = "SELECT * FROM employee  where  working_field='Worker' limit :limit offset :offset ",nativeQuery = true)
	List <Employee> findAllWorkerNQ(@Param("offset") int offset,@Param("limit") int limit);
	
	@Query(value = "SELECT * FROM employee  where  working_field='Admin' limit :limit offset :offset ",nativeQuery = true)
	List <Employee> findAllAdminNQ(@Param("offset") int offset,@Param("limit") int limit);
	
	@Query(value = "SELECT * FROM employee  where  working_field='Owner' limit :limit offset :offset ",nativeQuery = true)
	List <Employee> findAllOwnerNQ(@Param("offset") int offset,@Param("limit") int limit);
	
}
