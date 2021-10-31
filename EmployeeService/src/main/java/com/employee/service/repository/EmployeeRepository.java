package com.employee.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.service.model.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByUuid(String uuid);

}
