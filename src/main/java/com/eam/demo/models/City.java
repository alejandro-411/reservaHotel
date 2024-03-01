package com.eam.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="City")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cityId;
	
	@Column(name="cityName")
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name = "departmentCityId", referencedColumnName = "departmentId")
	private Department department;
	
	public City() {
		
	}

	public City(long cityId, String cityName, Department department) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.department = department;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
}
