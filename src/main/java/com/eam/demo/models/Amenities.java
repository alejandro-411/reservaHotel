package com.eam.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Amenities")
public class Amenities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long amenitiesId;
	
	@Column(name = "amenitiesName")
	private String amenitiesName;
	
	
	
}
