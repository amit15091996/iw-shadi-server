package com.shadi.controller;

import com.shadi.entity.UserLifeStyleAndEducation;
import com.shadi.exception.GenericException;
import com.shadi.service.UserLifeStyleAndEducationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserLifeStyleAndEducationController {

	@Autowired
	private UserLifeStyleAndEducationService userLifeStyleAndEducationService;

	@PostMapping("/save-user-life-style")
	public ResponseEntity<Map<String, Object>> saveUserLifeStyleAndEducationDetails(
			@RequestBody UserLifeStyleAndEducation userLifeStyleAndEducation) {
		try {
			Map<String, Object> response = userLifeStyleAndEducationService
					.saveUserLifeStyleAndEducationDetails(userLifeStyleAndEducation);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (GenericException e) {
			return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user-life-style-details")
	public ResponseEntity<Map<String, Object>> getUserLifeStyleAndEducationDetails(@RequestParam int page,
			@RequestParam int size) {
		try {
			Map<String, Object> response = userLifeStyleAndEducationService.getUserLifeStyleAndEducationDetails(page,
					size);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (GenericException e) {
			return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user-life-style-details/{id}")
	public ResponseEntity<Map<String, Object>> getUserLifeStyleAndEducationDetailsById(@PathVariable Long id) {
		try {
			Map<String, Object> response = userLifeStyleAndEducationService.getUserLifeStyleAndEducationDetailsById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (GenericException e) {
			return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-user-life-style-details/{id}")
	public ResponseEntity<Map<String, Object>> updateUserLifeStyleAndEducationDetails(@PathVariable Long id,
			@RequestBody UserLifeStyleAndEducation updatedDetails) {
		try {
			Map<String, Object> response = userLifeStyleAndEducationService.updateUserLifeStyleAndEducationDetails(id,
					updatedDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (GenericException e) {
			return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}