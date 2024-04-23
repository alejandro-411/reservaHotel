package com.eam.demo.models;

import java.util.List;

import jakarta.persistence.*;

@Table(name = "RoomType")
@Entity
public class RoomType {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long typeRoomId;
	
	@Column(name = "roomTypeName")
	private String roomTypeName;
	
	public RoomType() {
		
	}

	public RoomType(long typeRoomId, String roomTypeName) {
		super();
		this.typeRoomId = typeRoomId;
		this.roomTypeName = roomTypeName;
	}

	public long getTypeRoomId() {
		return typeRoomId;
	}

	public void setTypeRoomId(long typeRoomId) {
		this.typeRoomId = typeRoomId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	
}
