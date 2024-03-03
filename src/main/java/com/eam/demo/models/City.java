package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

//Clase mapeada
@Entity
@Table(name="Cities")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cityId;

	@Column(name = "cityName")
	private String cityName;

	@ManyToOne
	@JoinColumn(name = "departments_id", referencedColumnName = "departmentId")
	private Department department;

	@OneToMany(mappedBy = "city")
	private List<Location> locations;
	
	public City() {

	}

	public City(long cityId, String cityName, Department department, List<Location> locations) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.department = department;
		this.locations = locations;
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

	public List<Location> getLocation() {
		return locations;
	}

	public void setLocation(List<Location> location) {
		this.locations = location;
	}

}
