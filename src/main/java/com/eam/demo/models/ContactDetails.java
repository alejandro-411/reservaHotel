package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

//Clase mapeada
@Table(name="ContactDetails")
@Entity
public class ContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactDetailsId;
	
	@Column(name = "contactName")
	private String contactName;
	
	@Column(name = "contactNumber")
	private String contactNumber;
	
	@Column(name = "contactAddress") 
	private String contactAddress;
	
	@Column(name = "contactEmail")
	private String contactEmail;
	
	@OneToMany(mappedBy = "contactDetails")
	private List<User> users;

	
	
	

	public ContactDetails() {
		super();
	}

	public ContactDetails(long contactDetailsId, String contactName, String contactNumber, String contactAddress,
			String contactEmail, List<User> users) {
		super();
		this.contactDetailsId = contactDetailsId;
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		this.contactAddress = contactAddress;
		this.contactEmail = contactEmail;
		this.users = users;
	}

	public Long getContactDetailsId() {
		return contactDetailsId;
	}

	public void setContactDetailsId(Long contactDetailsId) {
		this.contactDetailsId = contactDetailsId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	
}
