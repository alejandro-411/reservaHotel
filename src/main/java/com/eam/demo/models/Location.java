package com.eam.demo.models;

import jakarta.persistence.*;

//Clase mapeada
@Entity
@Table(name = "Locations")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long locationId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@ManyToOne
	@JoinColumn(name = "cityLocationId", referencedColumnName = "departmentId")
	private City city;
	
	
	@JoinColumn(name = "LocationHotel", referencedColumnName = "hotelId")
	private Hotel hotel;

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Location() {
		
	}
	
	public Location(long locationId, String address, String neighborhood, City city) {
		this.locationId = locationId;
		this.address = address;
		this.neighborhood = neighborhood;
		this.city = city;
	}
	
	
	
}
