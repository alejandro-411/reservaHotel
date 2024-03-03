package com.eam.demo.models;

import jakarta.persistence.*;

@Table(name = "Rooms")
@Entity
public class Room {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long roomId;
	
	@Column(name = "roomNumber")
	private int roomNumber;
	
	@Column(name = "pricePerNight")
	private double pricePerNight;
	
	@Column(name = "availability")
	private boolean avalability;
	
	@ManyToOne
	@JoinColumn(name = "roomTypeId", referencedColumnName = "typerRoomId")
	private RoomType roomType;
}
