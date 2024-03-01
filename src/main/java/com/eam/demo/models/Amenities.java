package com.eam.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Amenities")
public class Amenities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long amenitiesId;
	
	@Column(name = "elevator")
	boolean elevator;
	
	@Column(name = "pool")
	boolean pool;
	
	@Column (name = "sauna")
	boolean sauna;
	
	@Column(name = "gym")
	boolean gym;

	public Amenities(long amenitiesId, boolean elevator, boolean pool, boolean sauna, boolean gym) {
		super();
		this.amenitiesId = amenitiesId;
		this.elevator = elevator;
		this.pool = pool;
		this.sauna = sauna;
		this.gym = gym;
	}
	
	public Amenities() {
		
	}

	public long getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(long amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public boolean isElevator() {
		return elevator;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public boolean isPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public boolean isSauna() {
		return sauna;
	}

	public void setSauna(boolean sauna) {
		this.sauna = sauna;
	}

	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}
	
	
}
