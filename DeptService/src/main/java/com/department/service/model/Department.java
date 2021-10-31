package com.department.service.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dept")

public class Department extends BaseEntity {

	
	@Column(name = "dept_name")
	private String deptName;
	
	
	@Column(name = "active")
	private boolean active;



	public Department() {

	}



	public Department(String deptName, boolean active) {
		super();
		this.deptName = deptName;
		this.active = active;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public boolean getActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}


	
}
