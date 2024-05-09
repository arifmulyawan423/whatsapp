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
@Table(name = "user_chat_room")
public class UserChatRoom {
	@Id
	@Column(name = "chat_room_id")
	@NotBlank(message = "Chat Room Id is mandatory")
	UUID chatRoomId;
	
	@NotBlank(message = "User Id is mandatory")
	@Column(name = "user_id")
	UUID userId;
	
	@NotBlank(message = "Friend User Id is mandatory")
	@Column(name = "friend_user_id")
	UUID friendUserId;
	
	@Column(name = "created_date")
	@CreationTimestamp
	Timestamp createdDate;

	public UUID getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(UUID chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(UUID friendUserId) {
		this.friendUserId = friendUserId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
