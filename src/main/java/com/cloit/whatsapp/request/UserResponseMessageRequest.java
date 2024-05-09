package com.cloit.whatsapp.request;

public class UserResponseMessageRequest {

	String userMessageId;
	String emojiCode;
	String responseUserId;
	
	public String getResponseUserId() {
		return responseUserId;
	}
	public void setResponseUserId(String responseUserId) {
		this.responseUserId = responseUserId;
	}
	public String getUserMessageId() {
		return userMessageId;
	}
	public void setUserMessageId(String userMessageId) {
		this.userMessageId = userMessageId;
	}
	public String getEmojiCode() {
		return emojiCode;
	}
	public void setEmojiCode(String emojiCode) {
		this.emojiCode = emojiCode;
	}
	
}
