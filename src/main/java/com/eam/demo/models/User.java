package com.eam.demo.models;

import jakarta.persistence.*;

@Table(name = "User")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "userPassword")
	private String userPassword;
	
	@Column(name = "userPhoneNumber")
	private String userPhoneNumber;
	
	@JoinColumn(name = "contactDetailsId", referencedColumnName = "contactDetailsId")
	private ContactDetails contactDetails;
	
	@JoinColumn(name = "userRolId", referencedColumnName = "rolId")
	private Rol rol;

	public User(long userId, String userName, String userEmail, String userPassword, String userPhoneNumber,
			ContactDetails contactDetails, Rol rol) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhoneNumber = userPhoneNumber;
		this.contactDetails = contactDetails;
		this.rol = rol;
	}
	
	public User() {
		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
	
	
	
}
