package com.shadi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shadi.profile.entity.UserRegistrationProfile;

@Repository
public interface UserProfileRegistrationRepo extends JpaRepository<UserRegistrationProfile, String> {

	Page<UserRegistrationProfile> findAll(Pageable pageable);

	Page<UserRegistrationProfile> findAllByGender(String gender, Pageable pageable);

	UserRegistrationProfile findByMobileNumber(String mobileNumber);
}
