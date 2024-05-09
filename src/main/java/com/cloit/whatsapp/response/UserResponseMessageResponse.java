package com.cloit.whatsapp.response;

public class UserResponseMessageResponse {
	
	UserMessageResponse userMessage;
	String emojiCode;
	
	public UserMessageResponse getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(UserMessageResponse userMessage) {
		this.userMessage = userMessage;
	}
	public String getEmojiCode() {
		return emojiCode;
	}
	public void setEmojiCode(String emojiCode) {
		this.emojiCode = emojiCode;
	}
	
}
