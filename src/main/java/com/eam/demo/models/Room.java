package com.eam.demo.models;

import jakarta.persistence.*;

@Table
@Entity
public class Room {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long roomId;
	
	@Column(name = "roomNumber")
	private int roomNumber;
	
	@Column(name = "pricePerNight")
	private double pricePerNight;
	
	@Column(name = "availability")
	private boolean avalability;
	
	@Column(name = "amenitiesDetails")
	private String amenitiesDetails;
	
	@ManyToOne
	@JoinColumn(name = "roomTypeId", referencedColumnName = "typerRoomId")
	private RoomType roomType;

    // Definición de la relación ManyToOne con la entidad Booking
    @ManyToOne
     @JoinColumn(name = "Booking_id", referencedColumnName = "idBooking")
     private Booking booking;
	
	public Room() {
		super();
	}

	public Room(long roomId, int roomNumber, double pricePerNight, boolean avalability, String amenitiesDetails,
			RoomType roomType, Booking booking) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.pricePerNight = pricePerNight;
		this.avalability = avalability;
		this.amenitiesDetails = amenitiesDetails;
		this.roomType = roomType;
		this.booking = booking;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public boolean isAvalability() {
		return avalability;
	}

	public void setAvalability(boolean avalability) {
		this.avalability = avalability;
	}

	public String getAmenitiesDetails() {
		return amenitiesDetails;
	}

	public void setAmenitiesDetails(String amenitiesDetails) {
		this.amenitiesDetails = amenitiesDetails;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}




	
	
}
