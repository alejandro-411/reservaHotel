package com.eam.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "Review")
@Entity
public class Review {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long idReview;	

	@Column(name = "tittleReview")	
	private String tittleReview;

	@ManyToOne
	@JoinColumn(name = "users_id", referencedColumnName = "userId")
	private User user; //el id del usuario

	@Column(name = "contentReview")
	private String contentReview; 

	// Definición de la relación ManyToOne con la entidad hotel
	@ManyToOne
	@JoinColumn(name = "hotels_id", referencedColumnName = "hotelId")
	private Hotel hotel;

	public Review() {
		super();
	}

	public Review(Long idReview, String tittleReview, User user, String contentReview, Hotel hotel) {
		super();
		this.idReview = idReview;
		this.tittleReview = tittleReview;
		this.user = user;
		this.contentReview = contentReview;
		this.hotel = hotel;
	}

	public Long getIdReview() {
		return idReview;
	}

	public void setIdReview(Long idReview) {
		this.idReview = idReview;
	}

	public String getTittleReview() {
		return tittleReview;
	}

	public void setTittleReview(String tittleReview) {
		this.tittleReview = tittleReview;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContentReview() {
		return contentReview;
	}

	public void setContentReview(String contentReview) {
		this.contentReview = contentReview;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	
	
}
