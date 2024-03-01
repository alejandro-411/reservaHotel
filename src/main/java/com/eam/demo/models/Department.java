package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="Department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //genera el id como un auto_increment
	private Long departmentId;
	
	@Column(name="departmentName")
	private String departmentName;
	
	
	
	@OneToMany(mappedBy = "department")
	private List<City> city;
	
	public Department() {
		
	}
	
	

	public Department(Long departmentId, String departmentName, List<City> city) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.city = city;
		System.out.println("Departamenot");
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
