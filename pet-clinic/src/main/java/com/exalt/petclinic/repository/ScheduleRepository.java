package com.exalt.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exalt.petclinic.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	Schedule findByEmployeeId(int employeeId);

	
	@Query(value = "select count(*) from schedual where id=:id ",nativeQuery = true)
	int findScheduleExistNQ(@Param("id") int  id);
	
	@Query(value = "select max(id) from schedual ",nativeQuery = true)
	int findScheduleIdNQ();

}
