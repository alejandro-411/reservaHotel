package com.eam.demo.models;

import jakarta.persistence.*;

@Table(name = "HotelAmenities")
@Entity
public class HotelAmenities {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long idHotelAmenities;
	
	 // Definición de la relación ManyToOne con la entidad Amenities
    @ManyToOne
	@JoinColumn(name = "amenities_idHA", referencedColumnName = "amenitiesId" )
	Amenities amenities;
    
    // Definición de la relación ManyToOne con la entidad Hotel
    @ManyToOne
	@JoinColumn(name = "hotel_idDA", referencedColumnName = "hotelId")
	Hotel hotel;

	

	public HotelAmenities() {

	}



	public HotelAmenities(Amenities amenities, Hotel hotel) {
		super();
		this.amenities = amenities;
		this.hotel = hotel;
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
