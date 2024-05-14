package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

//clase mapeada
@Entity
@Table(name="Departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //genera el id como un auto_increment
	private Long departmentId;
	
	@Column(name="departmentName")
	private String departmentName;
	
	
	public Department() {
		
	}
	

	public Department(Long departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}


	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
}
