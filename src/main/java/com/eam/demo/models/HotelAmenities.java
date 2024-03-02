package com.eam.demo.models;

import jakarta.persistence.*;

@Table(name = "HotelAmenities")
@Entity
public class HotelAmenities {
	
	@JoinColumn(name = "HotelAmenitiesId", referencedColumnName = "amenitiesId" )
	Amenities amenities;
	
	@JoinColumn(name = "Hotel")
	Hotel hotel;

	public HotelAmenities(Amenities amenities, Hotel hotel) {
		super();
		this.amenities = amenities;
		this.hotel = hotel;
	}

	public HotelAmenities() {

	}

	public Amenities getAmenities() {
		return amenities;
	}

	public void setAmenities(Amenities amenities) {
		this.amenities = amenities;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
}
