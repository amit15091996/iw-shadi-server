package com.shadi.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shadi.entity.UserPersonalDetails;
import com.shadi.exception.GenericException;
import com.shadi.repo.UserPersonalDetailsRepo;
import com.shadi.service.UserPersonalDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserPersonalDetailsServiceImpl implements UserPersonalDetailsService {

	@Autowired
	private UserPersonalDetailsRepo userPersonalDetailsRepo;

	@Override
	public Map<String, Object> saveUserPersonalDetails(UserPersonalDetails userPersonalDetails) {
		Map<String, Object> map = new HashMap<>();
		try {
			userPersonalDetails.setBirthPlace(userPersonalDetails.getBirthPlace());
			userPersonalDetails.setBloodGroup(userPersonalDetails.getBloodGroup());
			userPersonalDetails.setBodyType(userPersonalDetails.getBodyType());
			userPersonalDetails.setComplexion(userPersonalDetails.getComplexion());
			userPersonalDetails.setGotra(userPersonalDetails.getGotra());
			userPersonalDetails.setHobbies(userPersonalDetails.getHobbies());
			userPersonalDetails.setIsPersonDisabled(userPersonalDetails.getIsPersonDisabled());
			userPersonalDetails.setIsUserStayingAlone(userPersonalDetails.getIsUserStayingAlone());
			userPersonalDetails.setManglik(userPersonalDetails.getManglik());
			userPersonalDetails.setMaritalStatus(userPersonalDetails.getMaritalStatus());
			userPersonalDetails.setRashi(userPersonalDetails.getRashi());
			userPersonalDetails.setUserHeight(userPersonalDetails.getUserHeight());
			userPersonalDetails.setUserIncome(userPersonalDetails.getUserIncome());
			userPersonalDetails.setUserWeight(userPersonalDetails.getUserWeight());
			UserPersonalDetails details = userPersonalDetailsRepo.save(userPersonalDetails);
			map.put("UserPersonalDetails", details);
			map.put("status", HttpStatus.OK.value());
			log.info("UserPersonalDetails saved for UserPersonalId : "+ userPersonalDetails.getUserPersonalId());

		} catch (Exception e) {
			log.error("Exception in userFamilylDetails  " + e.getMessage(),e);
			throw new GenericException("Error While Saving Details");
		}
		return map;
	}

	@Override
	public Map<String, Object> getUserPersonalDetails(int page, int size) {
		Map<String, Object> map = new HashMap<>();
		try {
			Pageable pageable = PageRequest.of(page, size);
			Page<UserPersonalDetails> findAll = userPersonalDetailsRepo.findAll(pageable);

			if (findAll.getContent().isEmpty()) {
				map.put("message", "No data found");
				map.put("UserPersonalDetails", Collections.emptyList());
				map.put("status", HttpStatus.OK.value());
			} else {
				map.put("UserPersonalDetails", findAll.getContent());
				map.put("status", HttpStatus.NOT_FOUND.value());
				log.info("UserPersonalDetails  : "+ findAll.getContent());
			}
			map.put("totalPages", findAll.getTotalPages());
			map.put("totalElements", findAll.getTotalElements());
			map.put("currentPage", findAll.getNumber());
			map.put("pageSize", findAll.getSize());
		} catch (Exception e) {
			log.error("Exception in getUserFamilylDetails: " + e.getMessage(), e);
			throw new GenericException("Error while fetching user family details");
		}
		return map;
	}

	@Override
    public Map<String, Object> getUserPersonalDetailsById(Long userPersonalId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Optional<UserPersonalDetails> details = userPersonalDetailsRepo.findById(userPersonalId);
            if (details.isPresent()) {
                map.put("UserPersonalDetails", details.get());
                map.put("status", HttpStatus.OK.value());
            } else {
                map.put("message", "No details found for ID: " + userPersonalId);
                map.put("status", HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Exception in getUserPersonalDetailsById: " + e.getMessage(), e);
            throw new GenericException("Error While Fetching Details by ID");
        }
        return map;
    }

	 @Override
	    public Map<String, Object> updateUserPersonalDetails(UserPersonalDetails userPersonalDetails) {
	        Map<String, Object> map = new HashMap<>();
	        try {
	            Optional<UserPersonalDetails> existingDetailsOpt = userPersonalDetailsRepo.findById(userPersonalDetails.getUserPersonalId());
	            if (existingDetailsOpt.isPresent()) {
	                UserPersonalDetails existingDetails = existingDetailsOpt.get();
	                existingDetails.setBirthPlace(userPersonalDetails.getBirthPlace());
	                existingDetails.setBloodGroup(userPersonalDetails.getBloodGroup());
	                existingDetails.setBodyType(userPersonalDetails.getBodyType());
	                existingDetails.setComplexion(userPersonalDetails.getComplexion());
	                existingDetails.setGotra(userPersonalDetails.getGotra());
	                existingDetails.setHobbies(userPersonalDetails.getHobbies());
	                existingDetails.setIsPersonDisabled(userPersonalDetails.getIsPersonDisabled());
	                existingDetails.setIsUserStayingAlone(userPersonalDetails.getIsUserStayingAlone());
	                existingDetails.setManglik(userPersonalDetails.getManglik());
	                existingDetails.setMaritalStatus(userPersonalDetails.getMaritalStatus());
	                existingDetails.setRashi(userPersonalDetails.getRashi());
	                existingDetails.setUserHeight(userPersonalDetails.getUserHeight());
	                existingDetails.setUserIncome(userPersonalDetails.getUserIncome());
	                existingDetails.setUserWeight(userPersonalDetails.getUserWeight());

	                UserPersonalDetails updatedDetails = userPersonalDetailsRepo.save(existingDetails);
	                map.put("UserPersonalDetails", updatedDetails);
	                map.put("status", HttpStatus.OK.value());
	            } else {
	                map.put("message", "No details found for ID: " + userPersonalDetails.getUserPersonalId());
	                map.put("status", HttpStatus.NOT_FOUND.value());
	            }
	        } catch (Exception e) {
	            log.error("Exception in updateUserPersonalDetails: " + e.getMessage(), e);
	            throw new GenericException("Error While Updating Details");
	        }
	        return map;
	    }

}
