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
	
	
	
	

	public ContactDetails() {
		super();
	}

	public ContactDetails( String contactName, String contactNumber) {
		super();
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		
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



	
}
