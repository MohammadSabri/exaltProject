package com.exalt.petclinic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exalt.petclinic.model.Client;
import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Pet;
import com.exalt.petclinic.model.Schedule;
import com.exalt.petclinic.repository.ScheduleRepository;

@SpringBootTest
public class ScheduleTest {
	@Autowired
	ScheduleRepository scheduleRepository;

	@Test
	void testAddSchedule() {
		Schedule schedule = new Schedule();
		schedule.setMedicalDescribtion("paraflow, santolaks");
		schedule.setPrice(100.0);
		schedule.setProblemDescribtion("nose aczima");
		//schedule.setDate(new Date(2020, 3, 11, 14, 24));
		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 3, 23, 4, 0);
		
		schedule.setDate(calendar.getTime());

		List<Schedule> schedules = new ArrayList<Schedule>();

		Employee employee = new Employee();
		employee.setId(3);

		Pet pet = new Pet();
		pet.setId(5);
		Client client = new Client();
		client.setId(1);// this step to set the id of the client
		pet.setClient(client);

		schedules.add(schedule);
		pet.setSchedule(schedules);
		employee.setSchedule(schedules);

		schedule.setPet(pet);
		schedule.setEmployee(employee);

		scheduleRepository.save(schedule);

	}
	@Test
	void testfindScheduleByEmployeeId() {
		
		System.out.println(scheduleRepository.findByEmployeeId(3).getEmployee().toString());
	}
}
