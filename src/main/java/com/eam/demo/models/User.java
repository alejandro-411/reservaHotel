package com.eam.demo.models;

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

//clase mapeada
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "userName",  unique=true)
	private String userName;

	@Column(name = "userEmail")
	private String userEmail;

	@Column(name = "userPassword")
	private  String userPassword;

	@Column(name = "userPhoneNumber")
	private String userPhoneNumber;

	// Definición de la relación ManyToOne con la entidad ContactDetails
	@ManyToOne
	@JoinColumn(name = "ContactDetails_id", referencedColumnName = "contactDetailsId")
	private ContactDetails contactDetails;
    
	// Definición de la relación ManyToOne con la entidad Rol
	@ManyToOne
	@JoinColumn(name = "rol_id", referencedColumnName = "rolId")
	private Rol rol;
	

    // Relación OneToMany con la clase Review
    @OneToMany(mappedBy = "user")
    private List<Review>  reviews;

    
    // Relación OneToMany con la clase Bookings
    @OneToMany(mappedBy = "user")
    private List<Booking>  bokings;


    
    
	public User() {
		super();
	}




	public User(Long userId, String userName, String userEmail, String userPassword, String userPhoneNumber,
			ContactDetails contactDetails, Rol rol, List<Review> reviews, List<Booking> bokings) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhoneNumber = userPhoneNumber;
		this.contactDetails = contactDetails;
		this.rol = rol;
		this.reviews = reviews;
		this.bokings = bokings;
	}







	public Long getUserId() {
		return userId;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getUserEmail() {
		return userEmail;
	}




	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}




	public String getUserPassword() {
		return userPassword;
	}




	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}




	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}




	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}




	public ContactDetails getContactDetails() {
		return contactDetails;
	}




	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}




	public Rol getRol() {
		return rol;
	}




	public void setRol(Rol rol) {
		this.rol = rol;
	}




	public List<Review> getReviews() {
		return reviews;
	}




	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}




	public List<Booking> getBokings() {
		return bokings;
	}




	public void setBokings(List<Booking> bokings) {
		this.bokings = bokings;
	}
    
    
    
    
}
