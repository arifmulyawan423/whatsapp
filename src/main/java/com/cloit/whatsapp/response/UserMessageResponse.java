package com.cloit.whatsapp.response;

import java.util.List;

public class UserMessageResponse {
	UserChatRoomResponse userChatRoom;
	String userMessageId;
	String userMessage;
	List<String> listEmojiCode;
	String fileName;
	String fileUrl;
	
	public List<String> getListEmojiCode() {
		return listEmojiCode;
	}
	public void setListEmojiCode(List<String> listEmojiCode) {
		this.listEmojiCode = listEmojiCode;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	public UserChatRoomResponse getUserChatRoom() {
		return userChatRoom;
	}
	public void setUserChatRoom(UserChatRoomResponse userChatRoom) {
		this.userChatRoom = userChatRoom;
	}
	public String getUserMessageId() {
		return userMessageId;
	}
	public void setUserMessageId(String userMessageId) {
		this.userMessageId = userMessageId;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
}
