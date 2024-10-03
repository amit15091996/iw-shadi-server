package com.shadi.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shadi.exception.GenericException;
import com.shadi.service.ViewAllService;
import com.shadi.utils.ErrorResponse;

@RestController
@RequestMapping("/api/v1/user")
public class ViewProfilesController {

	@Autowired
	private ViewAllService viewAllService;

	@GetMapping("/profiles")
	public ResponseEntity<Map<String, Object>> viewAllDetails(@RequestParam(required = false) String gender,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "firstName") String sortBy) {

		Map<String, Object> map;
		try {
			map = viewAllService.viewAll(gender, page, size, sortBy);
		} catch (Exception e) {

			throw new GenericException(e);
		}
		return ResponseEntity.ok().body(map);
	}

	@GetMapping(value = "/profile-image", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> profileImage(@RequestParam String mobileNumber) {
		try {
			byte[] image = viewAllService.viewProfileImage(mobileNumber);
			if (Objects.nonNull(image)) {
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse("An error occurred while retrieving the profile image.",
					e.getMessage());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@GetMapping("/get-user-all-details")
	public ResponseEntity<?> getAllUserDetails(@RequestParam String mobileNumber) {

		return ResponseEntity.ok(this.viewAllService.getAllUserDetails(mobileNumber));
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<Map<Object, Object>> resetPasswordMail(
			@RequestParam(required = true) String mobileNumber,
			@RequestParam(required = true) String dateOfBirth) {
		return ResponseEntity.ok(this.viewAllService.resetPassword(mobileNumber, dateOfBirth));
	}
}
