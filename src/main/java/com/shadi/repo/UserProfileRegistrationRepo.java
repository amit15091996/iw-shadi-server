package com.shadi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shadi.profile.entity.UserRegistrationProfile;

@Repository
public interface UserProfileRegistrationRepo extends JpaRepository<UserRegistrationProfile, String> {

}
