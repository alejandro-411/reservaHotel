package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "ReserveStatus")
@Entity
public class ReserveStatus {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id	
	private int idRerserveStatus;

	@Column(name = "nameReserveStatus")
	private String nameReserveStatus;

	// Relación OneToMany con la clase Bookings
	@OneToMany(mappedBy = "reserveStatus")
	private List<Booking> Bookings;




	public ReserveStatus() {
		super();
	}



	public ReserveStatus(int idRerserveStatus, String nameReserveStatus, List<Booking> bookings) {
		super();
		this.idRerserveStatus = idRerserveStatus;
		this.nameReserveStatus = nameReserveStatus;
		Bookings = bookings;
	}



	public int getIdRerserveStatus() {
		return idRerserveStatus;
	}



	public void setIdRerserveStatus(int idRerserveStatus) {
		this.idRerserveStatus = idRerserveStatus;
	}



	public String getNameReserveStatus() {
		return nameReserveStatus;
	}



	public void setNameReserveStatus(String nameReserveStatus) {
		this.nameReserveStatus = nameReserveStatus;
	}



	public List<Booking> getBookings() {
		return Bookings;
	}



	public void setBookings(List<Booking> bookings) {
		Bookings = bookings;
	}



}
