package com.employee.service.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.employee.service.constant.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
public class Employee extends BaseEntity {

	@Column(name = "code_num", unique = true)
	private String code;

	@Column(name = "emp_name")
	private String empName;

	@Column(name = "mob_no")
	private String mobNo;

	@Column(name = "date_of_birth")
	private Date dateOfB;

	@Column(name = "d_ID")
	private Long deptid;

	
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public Employee() {

	}







	@PrePersist
	public void uniqueGen() {
		String substring = UUID.randomUUID().toString();
		setCode(substring.substring(0, 4));

	}
}
