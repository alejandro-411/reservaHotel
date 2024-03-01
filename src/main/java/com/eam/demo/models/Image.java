package com.eam.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long imageId;
	
	@Column(name = "imageLocation")
	String imageLocation;
	
	@Column(name = "imageName")
	String imageName;

	public Image(long imageId, String imageLocation, String imageName) {
		this.imageId = imageId;
		this.imageLocation = imageLocation;
		this.imageName = imageName;
	}
	
	public Image () {
		
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
	
	
	
}
