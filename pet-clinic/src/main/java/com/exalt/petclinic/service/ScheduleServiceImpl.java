package com.exalt.petclinic.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.dto.ScheduleDto;
import com.exalt.petclinic.dto.ScheduleMapper;
import com.exalt.petclinic.dto.ScheduleUpdateDto;
import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Schedule;
import com.exalt.petclinic.repository.ClientRepository;
import com.exalt.petclinic.repository.EmployeeRepository;
import com.exalt.petclinic.repository.PetRepository;
import com.exalt.petclinic.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	private ScheduleMapper scheduleMapper = Mappers.getMapper(ScheduleMapper.class);

	@Override
	@Transactional
	public ScheduleDto create(ScheduleUpdateDto scheduleUpdateDto) {

		if (employeeRepository.findWorkerExistNQ(scheduleUpdateDto.getEmployeeId()) == 0) {
			throw new CommonException(ErrorEnum.WORKER_NOT_FOUND);
		}
		if (petRepository.findPetExistNQ(scheduleUpdateDto.getPetId()) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		Schedule schedule = scheduleMapper.updateDtoToSchedule(scheduleUpdateDto);
		schedule.setCreationDate(Calendar.getInstance().getTime());
		return scheduleMapper.scheduleToDto(scheduleRepository.save(schedule));
	}

	@Override
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public List<ScheduleDto> getAll(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		Pageable pageable = PageRequest.of((page - 1), limit);
		Page<Schedule> pagedResult = scheduleRepository.findAll(pageable);
		return scheduleMapper.scheduleToDto(pagedResult.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ScheduleDto> getAllByWorkerId(int id, int page, int limit) {
		if (employeeRepository.findWorkerExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.WORKER_NOT_FOUND);
		}
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}

		return scheduleMapper.scheduleToDto(scheduleRepository.findScheduleByWorkerIdNQ(id, (page - 1) * limit, limit));
	}

	@Override
	@Transactional(readOnly = true)
	public List<ScheduleDto> getAllByPetId(int id, int page, int limit) {
		if (petRepository.findPetExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return scheduleMapper.scheduleToDto(scheduleRepository.findScheduleByPetIdNQ(id, (page - 1) * limit, limit));
	}

	@Override
	@Transactional(readOnly = true)
	public List<ScheduleDto> getAllByClientId(int id, int page, int limit) {
		if (clientRepository.findClientExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		}
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return scheduleMapper.scheduleToDto(scheduleRepository.findScheduleByClientIdNQ(id, (page - 1) * limit, limit));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ScheduleDto update(int id, ScheduleUpdateDto scheduleUpdateDto) {
		if (id < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (scheduleRepository.findScheduleExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.SCHEDULE_NOT_FOUND);
		}
		if (employeeRepository.findWorkerExistNQ(scheduleUpdateDto.getEmployeeId()) == 0) {
			throw new CommonException(ErrorEnum.WORKER_NOT_FOUND);
		}
		if (petRepository.findPetExistNQ(scheduleUpdateDto.getPetId()) == 0) {
			throw new CommonException(ErrorEnum.PET_NOT_FOUND);
		}

		Schedule schedule = scheduleRepository.findById(id).get();
		Date date = schedule.getCreationDate();
		schedule = scheduleMapper.updateDtoToSchedule(scheduleUpdateDto);
		schedule.setCreationDate(date);
		schedule.setId(id);
		scheduleRepository.save(schedule);
		return scheduleMapper.scheduleToDto(schedule);
	}

	@Override
	@Transactional
	public String delete(int id) {
		if (id < 1) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (scheduleRepository.findScheduleExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.SCHEDULE_NOT_FOUND);
		}
		scheduleRepository.deleteScheduleByIdNQ(id);
		if (scheduleRepository.findScheduleExistNQ(id) == 0) {
			return "Deleted sucsessfuly";
		} else {
			return "Schedule Not Deleted";
		}
	}

}
