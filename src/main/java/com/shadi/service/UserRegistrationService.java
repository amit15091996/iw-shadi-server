package com.shadi.service;

import java.util.Map;

import com.shadi.profile.dto.UserRegistrationProfileDto;

public interface UserRegistrationService {

	public Map<String, Object> createProfile(UserRegistrationProfileDto dto);
	
	public Map<String, Object> userLogin(String mobileNumber, String password);
	
	public Map<String, Object> updateProfile(UserRegistrationProfileDto dto);
	
	public Map<String, Object> forgotPassword(String mobileNumber);
}
