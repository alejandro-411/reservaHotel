package com.eam.demo.models;

import jakarta.persistence.*;

@Table(name = "Hotel")
@Entity
public class Hotel {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long hotelId;
	
	@Column(name = "hotelName")
	private String hotelName;
	
	@Column (name = "availability")
	private boolean availability;
	
	@Column(name = "rating")
	private int rating;
	
	@OneToOne
	@JoinColumn(name = "idLocationHotel", referencedColumnName = "locationId")
	Location location;
	
	
	
	
	public Hotel(long hotelId, String hotelName, boolean availability, int rating, Location location) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.availability = availability;
		this.rating = rating;
		this.location = location;
	}




	public Hotel() {
		
	}




	public long getHotelId() {
		return hotelId;
	}




	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}




	public String getHotelName() {
		return hotelName;
	}




	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}




	public boolean isAvailability() {
		return availability;
	}




	public void setAvailability(boolean availability) {
		this.availability = availability;
	}




	public int getRating() {
		return rating;
	}




	public void setRating(int rating) {
		this.rating = rating;
	}




	public Location getLocation() {
		return location;
	}




	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
}
