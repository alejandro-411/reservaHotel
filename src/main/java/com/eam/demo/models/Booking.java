package com.eam.demo.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "Booking")
@Entity
public class Booking {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int idBooking;

	@Column(name = "reservationEntryDate")
	Date reservationEntryDate;
	@Column(name = "reservationDepartureDate")
	Date reservationDepartureDate;
	@Column(name = "totalReservationPrice")
	double totalReservationPrice;


	User user;
	Hotel hotel;
	
	 // Relación OneToMany con la clase Room
    @OneToMany(mappedBy = "booking")
    private List<Room> rooms;
	
	
	
	 // Definición de la relación ManyToOne con la entidad ReserveStatus
    @ManyToOne
    @JoinColumn(name = "ReserveStatus_id", referencedColumnName = "idRerserveStatus")
   ReserveStatus reserveStatus;
    
}
