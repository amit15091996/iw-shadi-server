package com.shadi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFamilyDetails {

	@TableGenerator(allocationSize = 10, initialValue = 0, name = "user_family_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_family_details_sequence")
	private long userFamilyId;
	private String fatherName;
	private String fatherOccupation;
	private String motherName;
	private String motherOccupation;
	private int noOfBrothers;
	private int noOfBrothersMarried;
	private int noOfSisters;
	private int noOfSistersMarried;
	private int noOfFamilyMembers;
	private String familyValue;
	private String familyDetails;
	private String familyStatus;
	private String maternalGotra;

}
