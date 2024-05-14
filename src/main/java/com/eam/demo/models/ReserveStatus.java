package com.eam.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "ReserveStatus")
@Entity
public class ReserveStatus {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id	
	private Long idRerserveStatus;

	@Column(name = "nameReserveStatus")
	private String nameReserveStatus;

	public ReserveStatus() {
		super();
	}



	public ReserveStatus(Long idRerserveStatus, String nameReserveStatus) {
		super();
		this.idRerserveStatus = idRerserveStatus;
		this.nameReserveStatus = nameReserveStatus;
	}



	public Long getIdRerserveStatus() {
		return idRerserveStatus;
	}



	public void setIdRerserveStatus(Long idRerserveStatus) {
		this.idRerserveStatus = idRerserveStatus;
	}



	public String getNameReserveStatus() {
		return nameReserveStatus;
	}



	public void setNameReserveStatus(String nameReserveStatus) {
		this.nameReserveStatus = nameReserveStatus;
	}

}
