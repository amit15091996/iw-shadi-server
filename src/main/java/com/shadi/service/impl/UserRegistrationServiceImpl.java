package com.shadi.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadi.expection.GenericException;
import com.shadi.profile.dto.UserRegistrationProfileDto;
import com.shadi.profile.entity.UserRegistrationProfile;
import com.shadi.repo.UserProfileRegistrationRepo;
import com.shadi.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserProfileRegistrationRepo profileRegistrationRepo;

	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public Map<String, Object> createProfile(UserRegistrationProfileDto dto) {
		Map<String, Object> map = new HashMap<>();
		try {
			// Check if a profile with the same mobileNumber already exists
			if (profileRegistrationRepo.existsById(dto.getMobileNumber())) {
				throw new GenericException("Profile with mobile number " + dto.getMobileNumber() + " already exists.");
			}

			var userRegistrationProfile = new UserRegistrationProfile();
			userRegistrationProfile.setMobileNumber(dto.getMobileNumber());
			userRegistrationProfile.setFirstName(dto.getFirstName());
			userRegistrationProfile.setLastName(dto.getLastName());
			userRegistrationProfile.setAge(dto.getAge());
			userRegistrationProfile.setGender(dto.getGender());
			userRegistrationProfile.setLangKnown(mapper.writeValueAsString(dto.getLangKnown()));
			userRegistrationProfile.setPassword(passwordEncoder.encode(dto.getPassword()));
			userRegistrationProfile.setConfirmPassword(passwordEncoder.encode(dto.getConfirmPassword()));
			userRegistrationProfile.setCommunity(dto.getCommunity());
			userRegistrationProfile.setDob(dto.getDob());
			userRegistrationProfile.setCreatedTime(LocalDateTime.now());
			userRegistrationProfile.setProfileImage(dto.getProfileImage().getBytes());

			profileRegistrationRepo.save(userRegistrationProfile);

			map.put("message", "Profile created successfully");
			// Optionally, you can also add userRegistrationProfile to map if needed
			// map.put("profileDetails", userRegistrationProfile);
		} catch (Exception e) {
			map.put("message", "Profile creation failed");
			map.put("error", e.getMessage()); // Add error message for better logging
			throw new GenericException(e);
		}
		return map;
	}
}
