package com.example.naviIT;

public class Path {

	private int pathID;
	private String currentRegion;
	private String nextRegion;
	private float degree;
	private int stepCount;
	
	public Path(String currentRegion, String nextRegion, float degree, int stepCount) {
		super();
		this.currentRegion = currentRegion;
		this.nextRegion = nextRegion;
		this.degree = degree;
		this.stepCount = stepCount;
	}
	
	public void setPathID(int pathID) {
		this.pathID = pathID;
	}
	
	public void setCurrentRegion(String currentRegion) {
		this.currentRegion = currentRegion;
	}
	
	public void setNextRegion(String nextRegion) {
		this.nextRegion = nextRegion;
	}
	
	public void setDegree(float degree) {
		this.degree = degree;
	}
	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}
	
	
	public int getPathID() {
		return pathID;
	}
	
	public String getCurrentRegion() {
		return currentRegion;
	}
	
	public String getNextRegion() {
		return nextRegion;
	}
	
	public float getDegree() {
		return degree;
	}
	
	public int getStepCount() {
		return stepCount;
	}
	
	@Override
	public String toString() {
		return "Path [pathID=" + pathID +", currentRegion=" + currentRegion + ", nextRegion=" + nextRegion + ", degree=" + degree + ", stepCount="
				+ stepCount +"]";
	}
	
}
