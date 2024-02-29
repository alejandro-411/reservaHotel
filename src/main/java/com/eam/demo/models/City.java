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
	
	private long departmentName;
}
