package com.cloit.whatsapp.request;

public class ResponseUserMessageRequest {
	String messageId;
	String messageResponseCode;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageResponseCode() {
		return messageResponseCode;
	}
	public void setMessageResponseCode(String messageResponseCode) {
		this.messageResponseCode = messageResponseCode;
	}
	
}
