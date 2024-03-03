package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;
//Clase mapeada
@Entity
@Table(name = "Amenities")
public class Amenities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long amenitiesId;
	
	@Column(name = "amenitiesName")
	private String amenitiesName;

	
	// Relación OneToMany con la clase HotelAmenities
    @OneToMany(mappedBy = "amenities")
    private List<HotelAmenities> hotelAmenities;
	
	public Amenities() {
		super();
	}

	
	
	public Amenities(long amenitiesId, String amenitiesName, List<HotelAmenities> hotelAmenities) {
		super();
		this.amenitiesId = amenitiesId;
		this.amenitiesName = amenitiesName;
		this.hotelAmenities = hotelAmenities;
	}



	public long getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(long amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public String getAmenitiesName() {
		return amenitiesName;
	}

	public void setAmenitiesName(String amenitiesName) {
		this.amenitiesName = amenitiesName;
	}

	public List<HotelAmenities> getHotelAmenities() {
		return hotelAmenities;
	}

	public void setHotelAmenities(List<HotelAmenities> hotelAmenities) {
		this.hotelAmenities = hotelAmenities;
	}


	
	
	
}
