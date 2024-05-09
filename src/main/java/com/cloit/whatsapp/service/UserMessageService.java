package com.cloit.whatsapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloit.whatsapp.entity.UserChatRoom;
import com.cloit.whatsapp.entity.UserMessage;
import com.cloit.whatsapp.entity.UserResponseMessage;
import com.cloit.whatsapp.repository.UserChatRoomRepository;
import com.cloit.whatsapp.repository.UserMessageRepository;
import com.cloit.whatsapp.repository.UserResponseMessageRepository;
import com.cloit.whatsapp.request.UserMessageRequest;
import com.cloit.whatsapp.response.UserChatRoomResponse;
import com.cloit.whatsapp.response.UserMessageListResponse;
import com.cloit.whatsapp.response.UserMessageResponse;

@Service
public class UserMessageService {
	@Autowired UserChatRoomRepository userChatRoomRepository;
	@Autowired UserMessageRepository userMessageRepository;
	@Autowired UserResponseMessageRepository userResponseMessageRepository;
	
	public ResponseEntity<UserMessageListResponse> getAllUserMessageList(String userChatRoomId, Integer pageNo, Integer pageSize) {
		UserMessageListResponse response = new UserMessageListResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		response.setRecordCount(userMessageRepository.count());
		
		Integer offset = pageNo <= 0 ? 0 : (pageNo - 1) * pageSize;
		
		List<UserMessage> listUserMessage = userMessageRepository.findAllUserMessageWithPagination(UUID.fromString(userChatRoomId), offset, pageSize);
		
		List<UserMessageResponse> listUserMessageResponse = new ArrayList<UserMessageResponse>();
		
		listUserMessage.forEach((userMessage) -> {
			UserMessageResponse userMessageResponse = new UserMessageResponse();
			
			userMessageResponse.setUserMessageId(userMessage.getUserMessageId().toString());
			userMessageResponse.setUserMessage(userMessage.getMessage());
			userMessageResponse.setFileName(userMessage.getFileName());
			userMessageResponse.setFileUrl(userMessage.getFileUrl());
			
			UserChatRoomResponse userChatRoom = new UserChatRoomResponse();
			userChatRoom.setUserChatRoomId(userMessage.getUserChatRoomId().toString());
			
			userMessageResponse.setUserChatRoom(userChatRoom);
			
			List<UserResponseMessage> listUserResponseMessage = userResponseMessageRepository.findByUserMessageId(userMessage.getUserMessageId());

			List<String> listEmojiCode = listUserResponseMessage.stream().map(m -> m.getEmojiCode()).collect(Collectors.toList());
			
			userMessageResponse.setListEmojiCode(listEmojiCode);
			
			listUserMessageResponse.add(userMessageResponse);
		});
		
		response.setListUserMessage(listUserMessageResponse);
		
		return result;
	}
	
	public ResponseEntity<UserMessageResponse> uploadAttachment(String chatRoomId, MultipartFile attachment, String fileUrl) {
		
		UserMessageResponse response = new UserMessageResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		Optional<UserChatRoom> optUserChatRoom = userChatRoomRepository.findById(UUID.fromString(chatRoomId));
		
		if (optUserChatRoom.isEmpty()) {
			return new ResponseEntity<UserMessageResponse>(HttpStatus.BAD_REQUEST);
		}
		
		UserChatRoom userChatRoom = optUserChatRoom.get();
		
		UserMessage userMessage = new UserMessage();
		userMessage.setUserMessageId(UUID.randomUUID());
		userMessage.setUserChatRoomId(userChatRoom.getChatRoomId());
		userMessage.setFileName(attachment.getOriginalFilename());
		userMessage.setFileUrl(fileUrl);
		
		userMessageRepository.save(userMessage);
		
		UserChatRoomResponse userChatRoomResponse = new UserChatRoomResponse();
		userChatRoomResponse.setUserChatRoomId(userChatRoom.getChatRoomId().toString());
		
		response.setUserChatRoom(userChatRoomResponse);
		response.setUserMessageId(userMessage.getUserMessageId().toString());
		response.setFileName(userMessage.getFileName());
		response.setFileUrl(userMessage.getFileUrl());
		
		return result;
	}
	
	public ResponseEntity<UserMessageResponse> createUserMessage(UserMessageRequest request) {
		
		UserMessageResponse response = new UserMessageResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		Optional<UserChatRoom> optUserChatRoom = userChatRoomRepository.findById(UUID.fromString(request.getChatRoomId()));
		
		if (optUserChatRoom.isEmpty()) {
			return new ResponseEntity<UserMessageResponse>(HttpStatus.BAD_REQUEST);
		}
		
		UserChatRoom userChatRoom = optUserChatRoom.get();
		
		UserMessage userMessage = new UserMessage();
		userMessage.setUserMessageId(UUID.randomUUID());
		userMessage.setUserChatRoomId(userChatRoom.getChatRoomId());
		userMessage.setMessage(request.getMessage());
		
		userMessageRepository.save(userMessage);
		
		UserChatRoomResponse userChatRoomResponse = new UserChatRoomResponse();
		userChatRoomResponse.setUserChatRoomId(userChatRoom.getChatRoomId().toString());
		
		response.setUserChatRoom(userChatRoomResponse);
		response.setUserMessage(userMessage.getMessage());
		response.setUserMessageId(userMessage.getUserMessageId().toString());
		
		return result;
	}
}
