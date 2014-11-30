package com.example.naviIT;

public class Region {

	private String regionId;
	private String block;
	private int floor;
	private String neighbour;
	private int x;
	private int y;
	
	public Region(String regionId, String block, int floor, String neighbour, int x, int y) {
		super();
		this.regionId = regionId;
		this.block = block;
		this.floor = floor;
		this.neighbour = neighbour;
		this.x = x;
		this.y = y;
	}

	public String getRegionId() {
		return regionId;
	}
	
	public String getBlock() {
		return block;
	}
	
	public int getFloor() {
		return floor;
	}

	public String getNeighbour() {
		return neighbour;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId +", block=" + block + ", floor=" + floor + ", neigbour="
				+ neighbour + ", x="+ x + ",y="+ y +"]";
	}
	
}
