package com.exalt.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.DTO.ScheduleDto;
import com.exalt.petclinic.DTO.ScheduleMapper;
import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Schedule;
import com.exalt.petclinic.repository.EmployeeRepository;
import com.exalt.petclinic.repository.PetRepository;
import com.exalt.petclinic.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private ScheduleMapper scheduleMapper;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ScheduleDto create(ScheduleDto scheduleDto) {
		if (scheduleDto.getEmployeeId() < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (employeeRepository.findWorkerExistNQ(scheduleDto.getEmployeeId()) == 0) {
			throw new CommonException(ErrorEnum.WORKER_NOT_FOUND);
		}

		if (scheduleDto.getPetId() < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (petRepository.findPetExistNQ(scheduleDto.getPetId())==0){
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		scheduleRepository.save(scheduleMapper.dtoToSchedule(scheduleDto));
		scheduleDto.setId(scheduleRepository.findScheduleIdNQ());
		return scheduleDto;
	}

	@Override
	public Schedule update(int id, Schedule schedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleDto get(int id) {
		if (id < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (scheduleRepository.findScheduleExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.SCHEDULE_NOT_FOUND);
		}
		return scheduleMapper.scheduleToDto(scheduleRepository.findById(id).get());
	}

	@Override
	public List<ScheduleDto> getAll(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		Pageable pageable = PageRequest.of((page - 1) * limit, limit);
		Page<Schedule> pagedResult = scheduleRepository.findAll(pageable);
		return scheduleMapper.scheduleToDto(pagedResult.toList());
	}

	@Override
	public String delete(int id) {
		if (id < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (scheduleRepository.findScheduleExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.SCHEDULE_NOT_FOUND);
		}
		scheduleRepository.deleteById(id);
		return "Deleted Sucsessfuly";
	}

}
