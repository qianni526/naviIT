package com.example.naviIT;

public class Post {

	private String postId;
	private String username;
	private String userId;
	private String timestamp;
	private String room;
	private String content;
	private String regionId;
	
	public Post(String postId, String userId, String username, String timestamp, String room, String content, String regionId) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.username = username;
		this.room = room;
		this.timestamp = timestamp;	
		this.content = content;
		this.regionId = regionId;
	}

	public String getUserId(){
		return userId;
	}
	
	public String getPostId() {
		return postId;
	}
	
	public String getUsername() {
		return username;
	}

	public String getroom() {
		return room;
	}
	
	public String getTimeStamp() {
		return timestamp;
	}
	
	
	public String getContent() {
		return content;
	}

	public String getRegionId() {
		return regionId;
	}
	
	@Override
	public String toString() {
		return "Post [postId=" + postId +", userId=" + userId + ", username=" + username + ", room=" + room + ", timestamp=" + timestamp +", content =" + content  +", regionId =" + regionId +"]";
	}

	
	
	
}
