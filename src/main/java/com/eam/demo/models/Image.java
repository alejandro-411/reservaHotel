package com.eam.demo.models;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//Clase mapeada
@Entity
@Table(name = "Images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;

	@Lob
    @Column(name = "image_location", columnDefinition = "MEDIUMBLOB")
    private String imageLocation;

	@Column(name = "imageName")
	private String imageName;


	// Definición de la relación ManyToOne con la entidad Hotel
	@ManyToOne
	@JoinColumn(name = "hotel_Id", referencedColumnName = "hotelId")
	private Hotel hotel;


	public Image() {
		super();
	}


	public Image(long imageId, String imageLocation, String imageName, Hotel hotel) {
		super();
		this.imageId = imageId;
		this.imageLocation = imageLocation;
		this.imageName = imageName;
		this.hotel = hotel;
	}


	public long getImageId() {
		return imageId;
	}


	public void setImageId(long imageId) {
		this.imageId = imageId;
	}


	public String  getImageLocation() {
		return this.imageLocation;
	}


	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public Long getHotel() {
		return hotel.getHotelId();
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


}