package com.eam.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Bookings")
@Entity
public class Booking {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long bookingId;
	
	@Column(name = "reservationEntryDate")
	private LocalDateTime reservationEntryDate;
	
	@Column(name = "reservationDepartureDate")
	private LocalDateTime reservationDepartureDate;
	
	@Column(name = "totalReservationPrice")
	private double totalReservationPrice;
	
	@OneToMany(mappedBy = "bookings")
	private List <Room> rooms;
	
	
}
