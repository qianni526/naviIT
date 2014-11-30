package com.example.naviIT;

public class Event {

	private String eventId;
	private String title;
	private String venue;
	private String date;
	private String hour;
	private String minute;
	private String description;
	
	public Event(String eventId, String title, String venue, String date, String hour, String minute, String description) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.venue = venue;
		this.date = date;
		this.hour = hour;
		this.minute = minute;	
		this.description = description;
	}

	public String getEventId() {
		return eventId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getVenue() {
		return venue;
	}

	public String getDate() {
		return date;
	}
	
	public String getHour() {
		return hour;
	}
	
	public String getMinute(){
		return minute;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId +", title=" + title + ", venue=" + venue + ", date="
				+ date + ", hour=" + hour +", minute ="+ minute+ ", desciption =" + description +"]";
	}

	
	
	
}
