package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

@Table(name = "Rol")
@Entity
public class Rol {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long rolId;
	
	@Column(name = "rolName")
	private String rolName;
	
	@OneToMany(mappedBy = "rol" )
	private List<User> user;

	public Rol(long rolId, String rolName, List<User> user) {
		super();
		this.rolId = rolId;
		this.rolName = rolName;
		this.user = user;
	}
	
	public Rol() {
		
	}

	public long getRolId() {
		return rolId;
	}

	public void setRolId(long rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
}
