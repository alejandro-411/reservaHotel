package com.eam.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;

	@Column(name = "imageLocation")
	private String imageLocation;

	@Column(name = "imageName")
	private String imageName;


	// Definición de la relación ManyToOne con la entidad Hotel
	@ManyToOne
	@JoinColumn(name = "hotel_id", referencedColumnName = "hotelId")
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


	public String getImageLocation() {
		return imageLocation;
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


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}





}