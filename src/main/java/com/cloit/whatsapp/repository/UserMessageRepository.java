package com.cloit.whatsapp.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cloit.whatsapp.entity.UserMessage;

public interface UserMessageRepository extends JpaRepository<UserMessage, UUID>{
	@Query(value = "select * from user_message where user_chat_room_id = :userChatRoomId order by created_date desc limit :pageSize offset :pageNo", nativeQuery = true)
	public List<UserMessage> findAllUserMessageWithPagination(@Param("userChatRoomId") UUID userChatRoomId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
