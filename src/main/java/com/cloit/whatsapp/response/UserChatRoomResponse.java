package com.cloit.whatsapp.response;

import java.time.LocalDateTime;

public class UserChatRoomResponse {
	String userChatRoomId;
	UserProfileResponse user;
	UserProfileResponse friendUser;
	LocalDateTime createdDate;
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserChatRoomId() {
		return userChatRoomId;
	}
	public void setUserChatRoomId(String userChatRoomId) {
		this.userChatRoomId = userChatRoomId;
	}
	public UserProfileResponse getUser() {
		return user;
	}
	public void setUser(UserProfileResponse user) {
		this.user = user;
	}
	public UserProfileResponse getFriendUser() {
		return friendUser;
	}
	public void setFriendUser(UserProfileResponse friendUser) {
		this.friendUser = friendUser;
	}
	
}
