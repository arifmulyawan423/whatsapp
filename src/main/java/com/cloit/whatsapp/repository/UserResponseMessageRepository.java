package com.cloit.whatsapp.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloit.whatsapp.entity.UserResponseMessage;

public interface UserResponseMessageRepository extends JpaRepository<UserResponseMessage, UUID>{
	public List<UserResponseMessage> findByUserMessageId(UUID userMessageId);
	public List<UserResponseMessage> findByUserMessageIdAndResponseUserId(UUID userMessageId, UUID responseUserId);
}
