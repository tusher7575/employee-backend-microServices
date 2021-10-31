package com.department.service.dto;




public class DepartmentReqDTO extends UuidIdHolderRequestBodyDTO {
	

	
	private String deptName;
	
	private boolean active;

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
