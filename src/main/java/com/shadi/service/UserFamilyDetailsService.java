package com.shadi.service;

import java.util.Map;

import com.shadi.entity.UserFamilyDetails;

public interface UserFamilyDetailsService {

	public Map<String, Object> saveUserFamilylDetails(UserFamilyDetails userFamilyDetails);

	Map<String, Object> getUserFamilylDetails(int page, int size);

	public Map<String, Object> getUserFamilylDetailsById(Long userFamilyId);

	public Map<String, Object> updateUserFamilylDetails(Long id, UserFamilyDetails updatedDetails);

}
