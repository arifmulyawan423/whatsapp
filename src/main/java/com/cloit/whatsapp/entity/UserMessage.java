package com.cloit.whatsapp.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_message")
public class UserMessage {
	@Id
	@Column(name = "user_message_id")
	@NotBlank(message = "User Message Id is mandatory")
	UUID userMessageId;
	
	@Column(name = "user_chat_room_id")
	@NotBlank(message = "User Chat Room Id is mandatory")
	UUID userChatRoomId;
	
	@Column(name = "message")
	String message;
	
	@Column(name = "created_date")
	@CreationTimestamp
	Timestamp createdDate;
	
	@Column(name = "file_name")
	String fileName;
	
	@Column(name = "file_url")
	String fileUrl;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public UUID getUserMessageId() {
		return userMessageId;
	}

	public void setUserMessageId(UUID userMessageId) {
		this.userMessageId = userMessageId;
	}

	public UUID getUserChatRoomId() {
		return userChatRoomId;
	}

	public void setUserChatRoomId(UUID userChatRoomId) {
		this.userChatRoomId = userChatRoomId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
