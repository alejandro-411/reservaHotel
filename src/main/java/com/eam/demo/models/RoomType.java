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
	
	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms;
	
	public RoomType() {
		
	}

	public RoomType(long typeRoomId, String roomTypeName, List<Room> room) {
		super();
		this.typeRoomId = typeRoomId;
		this.roomTypeName = roomTypeName;
		this.rooms = room;
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

	public List<Room> getRoom() {
		return rooms;
	}

	public void setRoom(List<Room> room) {
		this.rooms = room;
	}
	
	
}
