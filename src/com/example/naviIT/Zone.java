package com.example.naviIT;

public class Zone {
	private String zone_id;
	private String neighbourA;
	private String neighbourB;
	private String neighbourC; 
	private String neighbourD; 
	
	public Zone(String zone_id, String neighbourA, String neighbourB, String neighbourC, String neighbourD) {
		super();
		this.zone_id = zone_id;
		this.neighbourA = neighbourA;
		this.neighbourB = neighbourB;
		this.neighbourC = neighbourC;
		this.neighbourC = neighbourD;
		
	}

	public String getZoneId() {
		return zone_id;
	}
	
	public String getNeighbourA() {
		return neighbourA;
	}

	public String getNeighbourB() {
		return neighbourB;
	}
	
	public String getNeighbourC() {
		return neighbourC;
	}
	
	public String getNeighbourD() {
		return neighbourD;
	}

	@Override
	public String toString() {
		return "Zone "+ zone_id;
	}

	
}
