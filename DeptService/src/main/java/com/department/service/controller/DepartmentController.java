package com.department.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.service.service.DepartmentService;
import com.department.service.exception.EmptyInputException;
import com.department.service.dto.DepartmentReqDTO;
import com.department.service.dto.DepartmentResponseDTO;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/all")
	public List<DepartmentResponseDTO> getAllDepartmentsNew() {
		return departmentService.findAll();
	}

	@GetMapping("/active")
	public List<DepartmentResponseDTO> getAllDepartmentsActive() {
		return departmentService.findAllActive();
	}

	@PostMapping("/save")
	public DepartmentResponseDTO saveDepartment(@RequestBody DepartmentReqDTO department) {

		if (department.getDeptName().isEmpty()) {

			throw new EmptyInputException("Department Name is Empty");
		}
		return departmentService.saveDepartment(department);

	}

	@GetMapping("/{uuid}")
	public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable String uuid) {

		DepartmentResponseDTO emRes = departmentService.findByUuid(uuid);

		return ResponseEntity.ok(emRes);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable String uuid,
			@RequestBody DepartmentReqDTO departmentReqDTO) {

		DepartmentResponseDTO deptRes = departmentService.updateEmp(uuid, departmentReqDTO);

		return ResponseEntity.ok(deptRes);

	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Map<String, Boolean>> deleteDepartmentuuid(@PathVariable(name = "uuid") String uuid) {

		Boolean res = departmentService.deleteByUuid(uuid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", res);
		return ResponseEntity.ok(response);
	}

}
