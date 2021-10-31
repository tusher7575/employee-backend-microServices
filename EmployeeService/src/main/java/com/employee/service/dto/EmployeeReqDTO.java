package com.employee.service.dto;

import java.util.Date;

import javax.persistence.Column;

import com.employee.service.constant.Enum.Gender;

import lombok.Data;

@Data
public class EmployeeReqDTO extends UuidIdHolderRequestBodyDTO {
	

	
	
	private String empName;

	
	private String mobNo;

	
	private Date dateOfB;

	
	private Long deptid;


	
	
	private Gender gender;


}
