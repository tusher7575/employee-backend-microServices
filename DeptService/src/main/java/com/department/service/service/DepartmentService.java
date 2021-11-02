package com.department.service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.service.dto.DepartmentReqDTO;
import com.department.service.dto.DepartmentResponseDTO;
import com.department.service.model.Department;
import com.department.service.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	public DepartmentResponseDTO saveDepartment(DepartmentReqDTO deptpreq) {

		Department dept = convertDtoToEntity(deptpreq);

		Department save = departmentRepository.save(dept);

		DepartmentResponseDTO resDTO = convertEntityToDto(save);

		return resDTO;

	}

	public List<DepartmentResponseDTO> findAll() {

		return departmentRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());

	}



	public Boolean deleteByUuid(String uuid) {

		Department dept = departmentRepository.findByUuid(uuid);

		departmentRepository.deleteById(dept.getId());

		return true;

	}

	public DepartmentResponseDTO updateEmp(String uuid, DepartmentReqDTO departmentDetails) {
		
		Department department = departmentRepository.findByUuid(uuid);
		
		department.setDeptName(departmentDetails.getDeptName());
		department.setActive(departmentDetails.getActive());
		
		
		Department save = departmentRepository.save(department);
		
		DepartmentResponseDTO convertEntityToDto = convertEntityToDto(save);
		
		
		return convertEntityToDto;
	}



	public DepartmentResponseDTO findByUuid(String uuid) {


		Department department = departmentRepository.findByUuid(uuid);
		
		DepartmentResponseDTO convertEntityToDto = convertEntityToDto(department);
		
		return convertEntityToDto;
	}

	public List<DepartmentResponseDTO> findAllActive() {
		
	return departmentRepository.findByActive(true).stream().map(this::convertEntityToDto).collect(Collectors.toList());
		
		
		
	
	}
	
	private DepartmentResponseDTO convertEntityToDto(Department dept) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		DepartmentResponseDTO DepartmentResponseDTO = new DepartmentResponseDTO();
		DepartmentResponseDTO = modelMapper.map(dept, DepartmentResponseDTO.class);
		return DepartmentResponseDTO;
	}

	private Department convertDtoToEntity(DepartmentReqDTO DepartmentReqDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Department dept = new Department();
		dept = modelMapper.map(DepartmentReqDTO, Department.class);
		return dept;
	}

}
