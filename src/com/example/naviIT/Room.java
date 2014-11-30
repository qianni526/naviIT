package com.example.naviIT;

public class Room {

	private String roomId;
	private String name;
	private String block;
	private String floor;
	private boolean isLecturerRoom;
	private String regionId;
	
	public Room(String roomId, String name, String block, String floor, boolean isLecturerRoom, String regionId) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.block = block;
		this.floor = floor;
		this.isLecturerRoom = isLecturerRoom;
		this.regionId = regionId;
	}

	public String getRoomId() {
		return roomId;
	}
	
	public String getName() {
		return name;
	}

	public String getBlock() {
		return block;
	}
	
	public String getFloor() {
		return floor;
	}

	public boolean getIsLecturerRoom() {
		return isLecturerRoom;
	}
	
	public String getRegionId(){
		return regionId;
	}

	@Override
	public String toString() {
		return "Rooms [roomId=" + roomId +", name=" + name + ", block=" + block + ", floor=" + floor + ", isLecturerRoom="
				+ isLecturerRoom + ", regionId="+ regionId + "]";
	}

	
	
	
}
