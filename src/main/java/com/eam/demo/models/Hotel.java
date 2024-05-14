package com.eam.demo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Table(name = "Hotels")
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

	@OneToOne(mappedBy = "hotel")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;

	public Hotel() {

	}

	public Hotel(long hotelId, String hotelName, boolean availability, int rating, Location location, User user) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.availability = availability;
		this.rating = rating;
		this.location = location;
		this.user =user;		
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

	public User getUser() {
		return user;
	}

	public void setUser(User  user) {
		this.user = user;
	}
	
}
