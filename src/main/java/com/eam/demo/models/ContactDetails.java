package com.eam.demo.models;

import jakarta.persistence.*;

@Table(name="ContactDetails")
@Entity
public class ContactDetails {
	
	@Id
	@GeneratedValue()
	private long contactDetailsId;

	private String contactName;

	private String contactNumber;

	private String contactAddress;

	private String contactEmail;

	

}
