package com.shadi.profile.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRegistrationProfile {

	@Id
	@Column(unique = true)
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	@Column(columnDefinition = "LONGTEXT")
	private String langKnown;
	@Column(nullable = false)
	private String password;
	private String religion;
	@Column(nullable = false)
	private String confirmPassword;
	private String community;
	private LocalDate dob;
	private String residence;
	private LocalDateTime createdTime;
	@Lob
	@Column(columnDefinition ="LONGBLOB")
	private byte[] profileImage;
}
