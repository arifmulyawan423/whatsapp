package com.cloit.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloit.whatsapp.request.UserChatRoomRequest;
import com.cloit.whatsapp.service.UserChatRoomService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Chat Room", description = "User Chat Room APIs")
@RestController
@RequestMapping("user_chat_room")
public class UserChatRoomController {
	@Autowired UserChatRoomService userChatRoomService;
	
	@PostMapping("/")
	public ResponseEntity createUserChatRoom(@RequestBody UserChatRoomRequest request) {
		return userChatRoomService.createUserChatRoom(request);
	}
	
	@GetMapping("/")
	public ResponseEntity getAllByUserId(@RequestParam("page_no") Integer pageNo, @RequestParam("page_size") Integer pageSize) {
		return userChatRoomService.getUserChatRoomList(pageNo, pageSize);
	}
}
