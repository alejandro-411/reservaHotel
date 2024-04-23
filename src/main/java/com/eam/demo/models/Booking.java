package com.eam.demo.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Long idBooking;

	@Column(name = "reservationEntryDate")
	Date reservationEntryDate;
	@Column(name = "reservationDepartureDate")
	Date reservationDepartureDate;
	@Column(name = "totalReservationPrice")
	double totalReservationPrice;

	// Definición de la relación ManyToOne con la entidad user
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	User user;


	// Definición de la relación ManyToOne con la entidad Hotel
	@ManyToOne
	@JoinColumn(name = "Hotel_id", referencedColumnName = "hotelId")
	@JsonIgnore
	Hotel hotel;

	// Relación OneToMany con la clase Room
	@OneToMany(mappedBy = "booking")
	private List<Room> rooms;


	// Definición de la relación ManyToOne con la entidad ReserveStatus
	@ManyToOne
	@JoinColumn(name = "ReserveStatus_id", referencedColumnName = "idRerserveStatus")
	ReserveStatus reserveStatus;


	public Booking() {
		super();
	}

	public Booking(Long idBooking, Date reservationEntryDate, Date reservationDepartureDate,
			double totalReservationPrice, User user, Hotel hotel, List<Room> rooms, ReserveStatus reserveStatus) {
		super();
		this.idBooking = idBooking;
		this.reservationEntryDate = reservationEntryDate;
		this.reservationDepartureDate = reservationDepartureDate;
		this.totalReservationPrice = totalReservationPrice;
		this.user = user;
		this.hotel = hotel;
		this.rooms = rooms;
		this.reserveStatus = reserveStatus;
	}

	public Long getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}

	public Date getReservationEntryDate() {
		return reservationEntryDate;
	}

	public void setReservationEntryDate(Date reservationEntryDate) {
		this.reservationEntryDate = reservationEntryDate;
	}

	public Date getReservationDepartureDate() {
		return reservationDepartureDate;
	}

	public void setReservationDepartureDate(Date reservationDepartureDate) {
		this.reservationDepartureDate = reservationDepartureDate;
	}

	public double getTotalReservationPrice() {
		return totalReservationPrice;
	}

	public void setTotalReservationPrice(double totalReservationPrice) {
		this.totalReservationPrice = totalReservationPrice;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Hotel getHotel() {
		return hotel;
	}



	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}



	public List<Room> getRooms() {
		return rooms;
	}



	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}



	public ReserveStatus getReserveStatus() {
		return reserveStatus;
	}



	public void setReserveStatus(ReserveStatus reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	
}
