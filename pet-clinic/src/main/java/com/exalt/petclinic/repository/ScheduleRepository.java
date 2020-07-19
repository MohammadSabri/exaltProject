package com.exalt.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	Schedule findByEmployeeId(int employeeId);

	@Transactional
	@Modifying
	@Query(value = "delete from schedual where id=:id ", nativeQuery = true)
	void deleteScheduleByIdNQ(@Param("id") int id);

	@Query(value = "select count(*) from schedual where id=:id ", nativeQuery = true)
	int findScheduleExistNQ(@Param("id") int id);

	@Query(value = "select max(id) from schedual ", nativeQuery = true)
	int findScheduleIdNQ();

	@Query(value = "select * from schedual where employee_id=:id limit :limit offset :offset", nativeQuery = true)
	List<Schedule> findScheduleByWorkerIdNQ(@Param("id") int id, @Param("offset") int offset,
			@Param("limit") int limit);

	@Query(value = "select * from schedual where pet_id=:id limit :limit offset :offset", nativeQuery = true)
	List<Schedule> findScheduleByPetIdNQ(@Param("id") int id, @Param("offset") int offset, @Param("limit") int limit);

	@Query(value = "select p.* from pet p join schedual s where p.id=s.pet_id and p.client_id =:id) limit :limit offset :offset", nativeQuery = true)
	List<Schedule> findScheduleByClientIdNQ(@Param("id") int id, @Param("offset") int offset,
			@Param("limit") int limit);

}
