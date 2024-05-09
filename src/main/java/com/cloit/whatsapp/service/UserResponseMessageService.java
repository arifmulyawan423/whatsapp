package com.cloit.whatsapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloit.whatsapp.entity.UserMessage;
import com.cloit.whatsapp.entity.UserResponseMessage;
import com.cloit.whatsapp.repository.UserMessageRepository;
import com.cloit.whatsapp.repository.UserResponseMessageRepository;
import com.cloit.whatsapp.request.UserResponseMessageRequest;
import com.cloit.whatsapp.response.UserMessageResponse;
import com.cloit.whatsapp.response.UserResponseMessageResponse;

@Service
public class UserResponseMessageService {
	
	@Autowired UserResponseMessageRepository userResponseMessageRepository;
	@Autowired UserMessageRepository userMessageRepository;
	
	public ResponseEntity<UserResponseMessageResponse> responseUserMessage(UserResponseMessageRequest request) {
		
		UserResponseMessageResponse response = new UserResponseMessageResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		Optional<UserMessage> optUserMessage = userMessageRepository.findById(UUID.fromString(request.getUserMessageId()));
	
		if (optUserMessage.isEmpty()) {
			return new ResponseEntity<UserResponseMessageResponse>(HttpStatus.BAD_REQUEST);
		}
		
		if (!Arrays.asList("thumbup", "love", "crying", "suprised").contains(request.getEmojiCode())) {
			return new ResponseEntity<UserResponseMessageResponse>(HttpStatus.BAD_REQUEST);
		}
		
		List<UserResponseMessage> listUserResponseMessage = userResponseMessageRepository.findByUserMessageIdAndResponseUserId(optUserMessage.get().getUserMessageId(), UUID.fromString(request.getResponseUserId()));
		
		List<String> listEmojiCode = listUserResponseMessage.stream().map(m -> m.getEmojiCode()).collect(Collectors.toList());
		
		if (!listEmojiCode.isEmpty() && listEmojiCode.contains(request.getEmojiCode())) {
			return new ResponseEntity<UserResponseMessageResponse>(HttpStatus.BAD_REQUEST);
		}
		
		UserResponseMessage userResponseMessage = new UserResponseMessage();
		userResponseMessage.setUserResponseMessageId(UUID.randomUUID());
		userResponseMessage.setUserMessageId(optUserMessage.get().getUserMessageId());
		userResponseMessage.setEmojiCode(request.getEmojiCode());
		userResponseMessage.setResponseUserId(UUID.fromString(request.getResponseUserId()));
		
		userResponseMessageRepository.save(userResponseMessage);
		
		listUserResponseMessage = userResponseMessageRepository.findByUserMessageId(optUserMessage.get().getUserMessageId());
		
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		
		userMessageResponse.setUserMessageId(optUserMessage.get().getUserMessageId().toString());
		
		listEmojiCode = listUserResponseMessage.stream().map(m -> m.getEmojiCode()).collect(Collectors.toList());
		
		userMessageResponse.setListEmojiCode(listEmojiCode);
		
		response.setUserMessage(userMessageResponse);
		response.setEmojiCode(request.getEmojiCode());
		
		return result;
	}
}
