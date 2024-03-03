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
	private String userId;

	@Column(name = "username")
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
    private List<Review>  review;

}
