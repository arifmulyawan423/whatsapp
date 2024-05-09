package com.cloit.whatsapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloit.whatsapp.entity.UserChatRoom;
import com.cloit.whatsapp.entity.UserProfile;
import com.cloit.whatsapp.repository.UserChatRoomRepository;
import com.cloit.whatsapp.repository.UserProfileRepository;
import com.cloit.whatsapp.request.UserChatRoomRequest;
import com.cloit.whatsapp.response.UserChatRoomListResponse;
import com.cloit.whatsapp.response.UserChatRoomResponse;
import com.cloit.whatsapp.response.UserProfileResponse;

@Service
public class UserChatRoomService {
	
	@Autowired UserChatRoomRepository userChatRoomRepository;
	@Autowired UserProfileRepository userProfileRepository;
	
	public ResponseEntity<UserChatRoomListResponse> getUserChatRoomList(Integer pageNo, Integer pageSize) {
		UserChatRoomListResponse response = new UserChatRoomListResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		response.setRecordCount(userChatRoomRepository.count());
		
		Integer offset = pageNo <= 0 ? 0 : (pageNo - 1) * pageSize;
		
		List<UserChatRoom> listUserChatRoom = userChatRoomRepository.findAllUserChatRoomWithPagination(offset, pageSize);
		
		List<UserChatRoomResponse> listUserChatRoomResponse = new ArrayList<UserChatRoomResponse>();
		
		listUserChatRoom.forEach((userChatRoom) -> {
			UserChatRoomResponse userChatRoomResponse = new UserChatRoomResponse();
			userChatRoomResponse.setUserChatRoomId(userChatRoom.getChatRoomId().toString());
			
			Optional<UserProfile> optUserProfile = userProfileRepository.findById(userChatRoom.getUserId());
			Optional<UserProfile> optFriendUserProfile = userProfileRepository.findById(userChatRoom.getFriendUserId());
			
			UserProfile userProfile = optUserProfile.get();
			UserProfile friendUserProfile = optFriendUserProfile.get();
			
			UserProfileResponse userResponse = new UserProfileResponse();
			userResponse.setUserId(userProfile.getUserId().toString());
			userResponse.setUserName(userProfile.getUserName());
			userResponse.setUserStatus(userProfile.getUserStatus());
			userResponse.setPhoneNumber(userProfile.getPhoneNumber());
			userChatRoomResponse.setUser(userResponse);
			
			UserProfileResponse friendUserResponse = new UserProfileResponse();
			friendUserResponse.setUserId(friendUserProfile.getUserId().toString());
			friendUserResponse.setUserName(friendUserProfile.getUserName());
			friendUserResponse.setUserStatus(friendUserProfile.getUserStatus());
			friendUserResponse.setPhoneNumber(friendUserProfile.getPhoneNumber());
			userChatRoomResponse.setFriendUser(friendUserResponse);
			
			userChatRoomResponse.setCreatedDate(userChatRoom.getCreatedDate().toLocalDateTime());
			
			listUserChatRoomResponse.add(userChatRoomResponse);
		});
		
		response.setListUserChatRoom(listUserChatRoomResponse);
		
		return result;
	}
	
	public ResponseEntity<UserChatRoomResponse> createUserChatRoom(UserChatRoomRequest request) {
		UserChatRoomResponse response = new UserChatRoomResponse();
		ResponseEntity result = ResponseEntity.ok(response);
	
		Optional<UserChatRoom> optUserChatRoom = userChatRoomRepository.findByUserIdAndFriendUserId(UUID.fromString(request.getUserId()), UUID.fromString(request.getFriendUserId()));
		
		if (optUserChatRoom.isPresent()) {
			return new ResponseEntity<UserChatRoomResponse>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<UserProfile> optUserProfile = userProfileRepository.findById(UUID.fromString(request.getUserId()));
		Optional<UserProfile> optFriendUserProfile = userProfileRepository.findById(UUID.fromString(request.getFriendUserId()));
		
		if (optUserProfile.isEmpty() || optFriendUserProfile.isEmpty()) {
			return new ResponseEntity<UserChatRoomResponse>(HttpStatus.BAD_REQUEST);
		}
		
		UserChatRoom userChatRoom = new UserChatRoom();
		userChatRoom.setChatRoomId(UUID.randomUUID());
		userChatRoom.setUserId(UUID.fromString(request.getUserId()));
		userChatRoom.setFriendUserId(UUID.fromString(request.getFriendUserId()));

		userChatRoomRepository.save(userChatRoom);
		
		response.setUserChatRoomId(userChatRoom.getChatRoomId().toString());
		
		UserProfile userProfile = optUserProfile.get();
		UserProfile friendUserProfile = optFriendUserProfile.get();
		
		UserProfileResponse userResponse = new UserProfileResponse();
		userResponse.setUserId(request.getUserId());
		userResponse.setUserName(userProfile.getUserName());
		userResponse.setUserStatus(userProfile.getUserStatus());
		userResponse.setPhoneNumber(userProfile.getPhoneNumber());
		response.setUser(userResponse);
		
		UserProfileResponse friendUserResponse = new UserProfileResponse();
		friendUserResponse.setUserId(request.getFriendUserId());
		friendUserResponse.setUserName(friendUserProfile.getUserName());
		friendUserResponse.setUserStatus(friendUserProfile.getUserStatus());
		friendUserResponse.setPhoneNumber(friendUserProfile.getPhoneNumber());
		response.setFriendUser(friendUserResponse);
		
		return result;
	}
}
