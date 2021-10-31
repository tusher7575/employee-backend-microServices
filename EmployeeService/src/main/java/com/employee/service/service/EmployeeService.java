package com.employee.service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.service.constant.Enum.Gender;
import com.employee.service.dto.EmployeeReqDTO;
import com.employee.service.dto.EmployeeResponseDTO;
import com.employee.service.model.Employee;
import com.employee.service.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public EmployeeResponseDTO saveEmployee(EmployeeReqDTO empreq) {

		Employee em = convertDtoToEntity(empreq);
//		em.setGenderTest(Gender.Female);

		Employee save = employeeRepository.save(em);

		EmployeeResponseDTO repDTO = convertEntityToDto(save);

		return repDTO;

	}

	public List<EmployeeResponseDTO> findAll() {

		return employeeRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());

	}

	private EmployeeResponseDTO convertEntityToDto(Employee emp) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
		employeeResponseDTO = modelMapper.map(emp, EmployeeResponseDTO.class);
		return employeeResponseDTO;
	}

	private Employee convertDtoToEntity(EmployeeReqDTO employeeReqDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Employee emp = new Employee();
		emp = modelMapper.map(employeeReqDTO, Employee.class);
		return emp;
	}

	public Boolean deleteByUuid(String uuid) {

		Employee em = employeeRepository.findByUuid(uuid);

		employeeRepository.deleteById(em.getId());

		return true;

	}

	public EmployeeResponseDTO updateEmp(String uuid, EmployeeReqDTO employeeDetails) {
		
		Employee employee = employeeRepository.findByUuid(uuid);
		
		employee.setEmpName(employeeDetails.getEmpName());
		employee.setDateOfB(employeeDetails.getDateOfB());
		employee.setDeptid(employeeDetails.getDeptid());
//		employee.setGender(employeeDetails.getGender());
		employee.setMobNo(employeeDetails.getMobNo());
		
		Employee save = employeeRepository.save(employee);
		
		EmployeeResponseDTO convertEntityToDto = convertEntityToDto(save);
		
		
		return convertEntityToDto;
	}



	public EmployeeResponseDTO findByUuid(String uuid) {


		Employee employee = employeeRepository.findByUuid(uuid);
		
		EmployeeResponseDTO convertEntityToDto = convertEntityToDto(employee);
		
		return convertEntityToDto;
	}

}
