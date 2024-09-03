package com.shadi.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shadi.profile.entity.UserRegistrationProfile;
import com.shadi.records.ProfileRecords;
import com.shadi.repo.UserProfileRegistrationRepo;
import com.shadi.service.ViewAllService;
import com.shadi.utils.AppConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViewAllServiceImpl implements ViewAllService {

    private static final Logger logger = LoggerFactory.getLogger(ViewAllServiceImpl.class);

    @Autowired
    private UserProfileRegistrationRepo userProfileRegistrationRepo;

    public Map<String, Object> viewAll(String gender, int page, int size, String sortBy) {
        Map<String, Object> map = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<UserRegistrationProfile> profilePage;
            
            profilePage = userProfileRegistrationRepo.findAll(pageable);
//            if (gender == null || gender.trim().isEmpty()) {
//                // Fetch all profiles if gender is null
//            } else {
//                // Fetch profiles based on opposite gender
//                String oppositeGender = getOppositeGender(gender);
//                profilePage = userProfileRegistrationRepo.findAllByGender(oppositeGender, pageable);
//            }
//            
            List<ProfileRecords> profiles = profilePage.getContent().stream()
                    .map(user -> new ProfileRecords(
                            user.getMobileNumber(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getAge(),
                            user.getGender(), // Ensure this is a String
                            String.join(", ", user.getLangKnown()), // Convert List<String> to String
                            user.getReligion(),
                            user.getCommunity(),
                            user.getDob(),
                            user.getResidence()
                    ))
                    .collect(Collectors.toList());

            map.put("result", profiles);
            map.put("currentPage", profilePage.getNumber());
            map.put("totalItems", profilePage.getTotalElements());
            map.put("totalPages", profilePage.getTotalPages());
        } catch (Exception e) {
            logger.error("Error occurred while fetching profiles", e); // Log the exception
            map.put("result", "error");
            map.put("message", e.getMessage()); // Include the error message in the response
        }
        return map;
    }



    private String getOppositeGender(String gender) {
        if (AppConstants.MALE.equalsIgnoreCase(gender)) {
            return AppConstants.FEMALE;
        } else if (AppConstants.FEMALE.equalsIgnoreCase(gender)) {
            return AppConstants.MALE;
        } else {
            throw new IllegalArgumentException("Invalid gender value: " + gender);
        }
    }

    @Override
    public byte[] viewProfileImage(String mobileNumber) {
        try {
            UserRegistrationProfile profile = userProfileRegistrationRepo.findByMobileNumber(mobileNumber);
            if (profile != null) {
                return profile.getProfileImage(); // Assuming this method exists in UserRegistrationProfile
            } else {
                throw new Exception("Profile not found for mobile number: " + mobileNumber);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading profile image", e);
        }
    }
}
