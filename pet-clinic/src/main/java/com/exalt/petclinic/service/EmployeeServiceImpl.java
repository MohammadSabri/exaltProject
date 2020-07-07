package com.exalt.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;
import com.exalt.petclinic.model.Employee;
import com.exalt.petclinic.model.Employee.WorkingField;
import com.exalt.petclinic.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployee(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			return employeeRepository.findById(id).get();
		}
	}

	@Override
	public List<Employee> getAllWorker(int page, int limit) {

		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return employeeRepository.findAllWorkerNQ((page - 1) * limit, limit);
	}

	@Override
	public Employee creatWorker(Employee employee) {

		employee.setWorkingField(WorkingField.Worker);// i think used for security
		if (employee.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}

		if (employeeRepository.findEmailExistNQ(employee.getEmail()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if (employeeRepository.findNumberExistNQ(employee.getPhoneNumber()) != 0)
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		employeeRepository.save(employee);

		return employee;
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {

		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}

		if (employeeRepository.findEmployeeExistNQ(id) == 0)
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);

		Employee employeeTemp = employeeRepository.findById(id).get();

		if (employee.getPhoneNumber().length() != 10)
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);

		if ((employeeRepository.findEmailExistNQ(employee.getEmail()) != 0)
				&& !(employee.getEmail().equals(employeeTemp.getEmail()))) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if ((employeeRepository.findNumberExistNQ(employee.getPhoneNumber()) != 0)
				&& !(employee.getPhoneNumber().equals(employeeTemp.getPhoneNumber())))
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		employeeTemp.setFirstName(employee.getFirstName());
		employeeTemp.setLastName(employee.getLastName());
		employeeTemp.setHouseLocation(employee.getHouseLocation());
		employeeTemp.setSalary(employee.getSalary());
		employeeTemp.setCollegeDegree(employee.getCollegeDegree());
		employeeTemp.setWorkingField(employee.getWorkingField());
		employeeTemp.setPhoneNumber(employee.getPhoneNumber());
		employeeTemp.setYearsOfExperience(employee.getYearsOfExperience());
		employeeTemp.setEmail(employee.getEmail());
		return employeeTemp;

	}

	@Override
	public String deleteWorker(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			employeeRepository.deleteById(id);
			return "Deleted sucsessfuly";
		}
	}

	@Override
	public List<Employee> getAllAdmin(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return employeeRepository.findAllAdminNQ((page - 1) * limit, limit);
	}

	@Override
	public Employee creatAdmin(Employee employee) {
		employee.setWorkingField(WorkingField.Admin);// i think used for security
		if (employee.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}

		if (employeeRepository.findEmailExistNQ(employee.getEmail()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if (employeeRepository.findNumberExistNQ(employee.getPhoneNumber()) != 0)
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		employeeRepository.save(employee);

		return employee;
	}

	@Override
	public String deleteAdmin(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			employeeRepository.deleteById(id);
			return "Deleted sucsessfuly";
		}
	}

	@Override
	public List<Employee> getAllOwner(int page, int limit) {
		if (page < 1) {
			throw new CommonException(ErrorEnum.PAGE_INVALID);
		}
		if (limit < 1) {
			throw new CommonException(ErrorEnum.LIMIT_INVALID);
		}
		return employeeRepository.findAllOwnerNQ((page - 1) * limit, limit);

	}

	@Override
	public Employee creatOwner(Employee employee) {
		employee.setWorkingField(WorkingField.Owner);// i think used for security
		if (employee.getPhoneNumber().length() != 10) {
			throw new CommonException(ErrorEnum.PHONE_NUMBER_INVALID);
		}

		if (employeeRepository.findEmailExistNQ(employee.getEmail()) != 0) {
			throw new CommonException(ErrorEnum.EXIST_EMAIL);
		}
		if (employeeRepository.findNumberExistNQ(employee.getPhoneNumber()) != 0)
			throw new CommonException(ErrorEnum.EXIST_PHONE_NUMBER);

		employeeRepository.save(employee);

		return employee;
	}

	@Override
	public String deleteOwner(int id) {
		if (id <= 0) {
			throw new CommonException(ErrorEnum.WRONG_ID_ENTERED);
		}
		if (employeeRepository.findEmployeeExistNQ(id) == 0) {
			throw new CommonException(ErrorEnum.CLIENT_NOT_FOUND);
		} else {
			employeeRepository.deleteById(id);
			return "Deleted sucsessfuly";
		}
	}
}
