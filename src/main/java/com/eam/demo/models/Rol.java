package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

//clase mapeada
@Table(name = "Rol")
@Entity
public class Rol {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long rolId;

	@Column(name = "rolName")
	private String rolName;


	public Rol(long rolId, String rolName, List<User> users) {
		super();
		this.rolId = rolId;
		this.rolName = rolName;
	}

	public Rol() {

	}


	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}



}
