package com.exalt.petclinic.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.petclinic.dto.EmployeeDto;
import com.exalt.petclinic.dto.EmployeeMapper;
import com.exalt.petclinic.dto.EmployeeUpdateDto;
import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

	@Override
	@Transactional(readOnly = true)
	public EmployeeDto getEmployee(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			return employeeMapper.employeeToDto(employeeRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDto> getAllWorker(int page, int limit) {

		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return employeeMapper.employeeToDto(employeeRepository.findAllWorkerNQ((page - 1) * limit, limit));
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDto> getAllOwner(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return employeeMapper.employeeToDto(employeeRepository.findAllOwnerNQ((page - 1) * limit, limit));

	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDto> getAllAdmin(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return employeeMapper.employeeToDto(employeeRepository.findAllAdminNQ((page - 1) * limit, limit));
	}

	@Override
	@Transactional
	public EmployeeDto creatEmployee(EmployeeUpdateDto employeeUpdateDto) {

		if (employeeUpdateDto.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}
		if (employeeRepository.findEmailExistNQ(employeeUpdateDto.getEmail()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if (employeeRepository.findNumberExistNQ(employeeUpdateDto.getPhoneNumber()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);
		}

		Employee employee = employeeMapper.updateDtoToEmployee(employeeUpdateDto);
		employee.setCreationDate(Calendar.getInstance().getTime());
	
		employee = employeeRepository.save(employee);

		return employeeMapper.employeeToDto(employee);
	}

	@Override
	@Transactional
	public String deleteEmployee(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		}

		employeeRepository.deleteById(id);
		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			return "Elmpoyee Deleted sucsessfuly";
		} else {
			return "Elmpoyee wasn't deleted";

		}

	}

	@Override
	@Transactional
	public EmployeeDto updateEmployee(int id, EmployeeUpdateDto employeeUpdateDto) {

		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (employeeRepository.findEmployeeExistNQ(id) == 0)
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);

		Employee employee = employeeRepository.findById(id).get();
		Date date = employee.getCreationDate();
		if (employeeUpdateDto.getPhoneNumber().length() != 10)
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);

		if ((employeeRepository.findEmailExistNQ(employeeUpdateDto.getEmail()) != 0)
				&& !(employeeUpdateDto.getEmail().equals(employee.getEmail()))) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if ((employeeRepository.findNumberExistNQ(employeeUpdateDto.getPhoneNumber()) != 0)
				&& !(employeeUpdateDto.getPhoneNumber().equals(employee.getPhoneNumber())))
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);
		employee = employeeMapper.updateDtoToEmployee(employeeUpdateDto);
		employee.setId(id);
		employee.setCreationDate(date);
		employeeRepository.save(employee);
		return employeeMapper.employeeToDto(employee);

	}

}
