package com.cloit.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloit.whatsapp.request.ResponseUserMessageRequest;
import com.cloit.whatsapp.request.UserMessageRequest;
import com.cloit.whatsapp.response.UserChatRoomListResponse;
import com.cloit.whatsapp.response.UserMessageResponse;
import com.cloit.whatsapp.service.FilesStorageService;
import com.cloit.whatsapp.service.UserMessageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Message", description = "User Message APIs")
@RestController
@RequestMapping("user_message")
public class UserMessageController {
	
	@Autowired UserMessageService userMessageService;
	@Autowired FilesStorageService storageService;
	
	@PostMapping("/")
	public ResponseEntity create(@RequestBody UserMessageRequest request) {
		return userMessageService.createUserMessage(request);
	}
	
	@PostMapping("/{chat_room_id}/send_attachment")
	public ResponseEntity sendAttachment(@PathVariable("chat_room_id") String chatRoomId, @RequestParam("file") MultipartFile file) {
		storageService.save(file);
		return userMessageService.uploadAttachment(chatRoomId, file, file.getOriginalFilename());
	}
	
	@GetMapping("/{chat_room_id}/get_all_by_room_id")
	public ResponseEntity getAllByChatRoomId(@PathVariable("chat_room_id") String chatRoomId, @RequestParam("page_no") Integer pageNo, @RequestParam("page_size") Integer pageSize) {
		return userMessageService.getAllUserMessageList(chatRoomId, pageNo, pageSize);
	}
}
