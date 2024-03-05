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

	@OneToMany(mappedBy = "rol" )
	private List<User> users;

	public Rol(long rolId, String rolName, List<User> users) {
		super();
		this.rolId = rolId;
		this.rolName = rolName;
		this.users = users;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	


}
