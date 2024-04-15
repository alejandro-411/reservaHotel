package com.eam.demo.models;

import java.util.List;

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


    // Relación OneToMany con la clase Review
    @OneToMany(mappedBy = "hotel")
    private List<Review>reviews;


 // Relación OneToMany con la clase Review
    @OneToMany(mappedBy = "hotel")
    private List<Image>  images;

    
 // Relación OneToMany con la clase HotelAmenities
    @OneToMany(mappedBy = "hotel")
    private List<HotelAmenities> hotelAmenities;
    
    // Relación OneToMany con la clase Booking
    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;

	public Hotel() {

	}

	public Hotel(long hotelId, String hotelName, boolean availability, int rating, Location location,
			 List<Image> images, List<HotelAmenities> hotelAmenities) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.availability = availability;
		this.rating = rating;
		this.location = location;
	    this.images = images;
		this.hotelAmenities = hotelAmenities;
		
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

	public List<Review> getReview() {
		return reviews;
	}

	public void setReview(List<Review> review) {
		this.reviews = review;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<HotelAmenities> getHotelAmenities() {
		return hotelAmenities;
	}

	public void setHotelAmenities(List<HotelAmenities> hotelAmenities) {
		this.hotelAmenities = hotelAmenities;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}


}
