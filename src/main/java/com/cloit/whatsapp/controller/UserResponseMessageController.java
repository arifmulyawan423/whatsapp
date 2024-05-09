package com.cloit.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloit.whatsapp.request.UserResponseMessageRequest;
import com.cloit.whatsapp.service.UserResponseMessageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Response Message", description = "User Response Message APIs")
@RestController
@RequestMapping("user_response_message")
public class UserResponseMessageController {

	@Autowired UserResponseMessageService userResponseMessageService;
	
	@PostMapping("/")
	public ResponseEntity createResponseMessage(@RequestBody UserResponseMessageRequest request) {
		return userResponseMessageService.responseUserMessage(request);
	}
}
