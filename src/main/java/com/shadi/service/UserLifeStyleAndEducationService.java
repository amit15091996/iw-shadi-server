package com.shadi.service;

import java.util.Map;

import com.shadi.entity.UserLifeStyleAndEducation;

public interface UserLifeStyleAndEducationService {
	
	public Map<String, Object> saveUserLifeStyleAndEducationDetails(UserLifeStyleAndEducation userLifeStyleAndEducation);

	public Map<String, Object> getUserLifeStyleAndEducationDetails(int page, int size);

	public Map<String, Object> getUserLifeStyleAndEducationDetailsById(Long userLifeStyleAndEducationId);

	public Map<String, Object> updateUserLifeStyleAndEducationDetails(Long id,UserLifeStyleAndEducation userLifeStyleAndEducation);
}