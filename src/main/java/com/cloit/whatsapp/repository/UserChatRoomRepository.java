package com.cloit.whatsapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cloit.whatsapp.entity.UserChatRoom;

public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, UUID>{
	public Optional<UserChatRoom> findByUserIdAndFriendUserId(UUID userId, UUID friendUserId);
	@Query(value = "select * from user_chat_room order by created_date desc limit :pageSize offset :pageNo", nativeQuery = true)
	public List<UserChatRoom> findAllUserChatRoomWithPagination(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
