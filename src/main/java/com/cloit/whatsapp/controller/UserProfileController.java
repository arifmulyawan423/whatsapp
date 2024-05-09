package com.cloit.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloit.whatsapp.request.UserProfileRequest;
import com.cloit.whatsapp.service.UserProfileService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Profile", description = "User Profile APIs")
@RestController
@RequestMapping("user_profile")
public class UserProfileController {
	@Autowired UserProfileService userProfileService;
	
	@PutMapping("/{id}")
	public ResponseEntity updateUserProfile(@PathVariable("id") String userId, @RequestBody UserProfileRequest request) {
		return userProfileService.updateUserProfile(userId, request);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getUserProfileByUserId(@PathVariable("id") String userId) {
		return userProfileService.getUserProfile(userId);
	}
}
