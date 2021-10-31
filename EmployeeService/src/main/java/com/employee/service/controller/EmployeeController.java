package com.employee.service.controller;

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

import com.employee.service.dto.EmployeeReqDTO;
import com.employee.service.dto.EmployeeResponseDTO;
import com.employee.service.exception.EmptyInputException;
import com.employee.service.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get all employees
	@GetMapping("/all")
	public List<EmployeeResponseDTO> getAllEmployeesNew() {
		return employeeService.findAll();
	}

	@PostMapping("/save")
	public EmployeeResponseDTO saveEmployee(@RequestBody EmployeeReqDTO employee) {
		if(employee.getEmpName().isEmpty() || employee.getEmpName().length()>35) {
			
			throw new EmptyInputException("Name Is Empty");
		}
		return employeeService.saveEmployee(employee);

	}

	@GetMapping("/{uuid}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable String uuid) {

		EmployeeResponseDTO emRes = employeeService.findByUuid(uuid);

		return ResponseEntity.ok(emRes);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable String uuid,
			@RequestBody EmployeeReqDTO employeeReqDTO) {

		EmployeeResponseDTO emRes = employeeService.updateEmp(uuid, employeeReqDTO);

		return ResponseEntity.ok(emRes);

	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployeeuuid(@PathVariable(name = "uuid") String uuid) {

		Boolean res = employeeService.deleteByUuid(uuid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", res);
		return ResponseEntity.ok(response);
	}

}
