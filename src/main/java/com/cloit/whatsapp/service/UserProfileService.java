package com.cloit.whatsapp.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloit.whatsapp.entity.UserProfile;
import com.cloit.whatsapp.repository.UserProfileRepository;
import com.cloit.whatsapp.request.UserProfileRequest;
import com.cloit.whatsapp.response.UserProfileResponse;

@Service
public class UserProfileService {

	@Autowired UserProfileRepository userProfileRepository;
	
	public ResponseEntity<UserProfileResponse> getUserProfile(String userId) {
		UserProfileResponse response = new UserProfileResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		Optional<UserProfile> optUserProfile = userProfileRepository.findById(UUID.fromString(userId));
		
		if (optUserProfile.isEmpty()) {
			return new ResponseEntity<UserProfileResponse>(HttpStatus.BAD_REQUEST);
		}
		
		UserProfile userProfile = optUserProfile.get();
		
		response.setUserId(userProfile.getUserId().toString());
		response.setUserName(userProfile.getUserName());
		response.setUserStatus(userProfile.getUserStatus());
		response.setPhoneNumber(userProfile.getPhoneNumber());
		
		return result;
	}
	
	public ResponseEntity<UserProfileResponse> updateUserProfile(String userId, UserProfileRequest request) {
		UserProfileResponse response = new UserProfileResponse();
		ResponseEntity result = ResponseEntity.ok(response);
		
		Optional<UserProfile> optUserProfile = userProfileRepository.findById(UUID.fromString(userId));
		
		if (optUserProfile.isEmpty()) {
			return new ResponseEntity<UserProfileResponse>(HttpStatus.BAD_REQUEST);
		}
		
		UserProfile userProfile = optUserProfile.get();
		
		userProfile.setUserName(request.getUserName());
		userProfile.setUserStatus(request.getUserStatus());
		userProfile.setPhoneNumber(request.getPhoneNumber());
		
		userProfileRepository.save(userProfile);
		
		response.setUserId(userProfile.getUserId().toString());
		response.setUserName(userProfile.getUserName());
		response.setUserStatus(userProfile.getUserStatus());
		response.setPhoneNumber(userProfile.getPhoneNumber());
		
		return result;
	}
}
