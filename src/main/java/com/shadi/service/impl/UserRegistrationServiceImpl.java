package com.shadi.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadi.config.JwtHelpers;
import com.shadi.expection.GenericException;
import com.shadi.expection.InternalServerError;
import com.shadi.profile.dto.UserRegistrationProfileDto;
import com.shadi.profile.entity.UserRegistrationProfile;
import com.shadi.profile.entity.UserRoles;
import com.shadi.repo.UserProfileRegistrationRepo;
import com.shadi.service.UserRegistrationService;
import com.shadi.utils.AppConstants;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserProfileRegistrationRepo profileRegistrationRepo;

	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtHelpers jwtHelpers;

	public Map<String, Object> createProfile(UserRegistrationProfileDto dto) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        if (profileRegistrationRepo.existsById(dto.getMobileNumber())) {
	            throw new GenericException("Profile with mobile number " + dto.getMobileNumber() + " already exists.");
	        }

	        var userRegistrationProfile = new UserRegistrationProfile();
	        userRegistrationProfile.setMobileNumber(dto.getMobileNumber());
	        userRegistrationProfile.setFirstName(dto.getFirstName());
	        userRegistrationProfile.setLastName(dto.getLastName());
	        userRegistrationProfile.setAge(dto.getAge());
	        userRegistrationProfile.setGender(dto.getGender());
	        userRegistrationProfile.setLangKnown(dto.getLangKnown()); // Directly use List<String>
	        userRegistrationProfile.setPassword(passwordEncoder.encode(dto.getPassword()));
	        userRegistrationProfile.setConfirmPassword(passwordEncoder.encode(dto.getConfirmPassword()));
	        userRegistrationProfile.setCommunity(dto.getCommunity());
	        userRegistrationProfile.setResidence(dto.getResidence());
	        userRegistrationProfile.setReligion(dto.getReligion());
	        userRegistrationProfile.setDob(dto.getDob());
	        userRegistrationProfile.setCreatedTime(LocalDateTime.now());

	        if (dto.getProfileImage() != null && !dto.getProfileImage().isEmpty()) {
	            userRegistrationProfile.setProfileImage(dto.getProfileImage().getBytes());
	            userRegistrationProfile.setExtension(dto.getProfileImage().getOriginalFilename());
	        }

	        var roles = new UserRoles();
	        roles.setRole("USER");
	        userRegistrationProfile.setUserRole(Arrays.asList(roles));
	        roles.setUserRegistrationProfile(userRegistrationProfile);
	        
	        profileRegistrationRepo.save(userRegistrationProfile);
	        map.put("message", "Profile created successfully");
	    } catch (Exception e) {
	        map.put("message", "Profile creation failed");
	        map.put("error", e.getMessage()); // Add error message for better logging
	    }
	    return map;
	}



	@Override
	public Map<String, Object> userLogin(String mobileNumber, String password) {
		

		Map<String, Object> userLoginMap = new HashMap<>();
		try {
			UserRegistrationProfile findByMobileNumber = this.profileRegistrationRepo.findByMobileNumber(mobileNumber);
			if (findByMobileNumber != null) {
				var jwtToken = this.jwtHelpers.generateToken(findByMobileNumber);
				userLoginMap.put(AppConstants.USER_NAME, findByMobileNumber.getMobileNumber());
				userLoginMap.put(AppConstants.FULL_NAME, findByMobileNumber.getFirstName() + " " + findByMobileNumber.getLastName());
				userLoginMap.put(AppConstants.JWT_TOKEN, jwtToken);
				userLoginMap.put(AppConstants.TOKEN_EXPIRATION_IN_MILIS,
						this.jwtHelpers.getExpirationDateFromToken(jwtToken).toInstant().toEpochMilli());
				userLoginMap.put(AppConstants.USER_ROLES, findByMobileNumber.getUserRole().stream()
						.map(role -> role.getRole()).collect(Collectors.toList()));
				userLoginMap.put(AppConstants.status, AppConstants.success);
				userLoginMap.put(AppConstants.statusCode, AppConstants.ok);
				userLoginMap.put(AppConstants.statusMessage, AppConstants.userLoggedInSuccesfully);
				return userLoginMap;
			} else {
				throw new com.shadi.expection.NotFoundException("Sorry No user found with the given mobile number : " + mobileNumber);
			}
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

	
	}
	
}
