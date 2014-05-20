package com.example.naviIT;

public class Event {

	private String eventId;
	private String title;
	private String venue;
	private String date;
	private String time;
	private String description;
	
	public Event(String eventId, String title, String venue, String date, String time, String description) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.venue = venue;
		this.date = date;
		this.time = time;
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
	
	public String getTime() {
		return time;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId +", title=" + title + ", venue=" + venue + ", date="
				+ date + ", time=" + time + ", desciption =" + description +"]";
	}

	
	
	
}
