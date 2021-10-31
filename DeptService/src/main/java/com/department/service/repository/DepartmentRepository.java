package com.department.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department.service.model.Department;



@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByUuid(String uuid);

	List<Department> findByActive(boolean b);

}
