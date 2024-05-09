package com.cloit.whatsapp.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_response_message")
public class UserResponseMessage {
	@Id
	@Column(name = "user_response_message_id")
	@NotBlank(message = "User Response Message Id is mandatory")
	UUID userResponseMessageId;
	
	@Column(name = "user_message_id")
	@NotBlank(message = "User Message Id is mandatory")
	UUID userMessageId;
	
	@Column(name = "emoji_code")
	@NotBlank(message = "Emoji Code is mandatory")
	String emojiCode;
	
	@Column(name = "response_user_id")
	@NotBlank(message = "Response User Id is mandatory")
	UUID responseUserId;
	
	public UUID getResponseUserId() {
		return responseUserId;
	}
	public void setResponseUserId(UUID responseUserId) {
		this.responseUserId = responseUserId;
	}
	public UUID getUserResponseMessageId() {
		return userResponseMessageId;
	}
	public void setUserResponseMessageId(UUID userResponseMessageId) {
		this.userResponseMessageId = userResponseMessageId;
	}
	public UUID getUserMessageId() {
		return userMessageId;
	}
	public void setUserMessageId(UUID userMessageId) {
		this.userMessageId = userMessageId;
	}
	public String getEmojiCode() {
		return emojiCode;
	}
	public void setEmojiCode(String emojiCode) {
		this.emojiCode = emojiCode;
	}
}
