package com.shadi.entity;

import java.util.List;

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
public class UserPartnerPreferences {

	@TableGenerator(allocationSize = 10, initialValue = 0, name = "user_partner_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_partner_sequence")
	private long userPartnerPreferencesId;
	private String familyStatus;
	private String familyValue;
	private List<String> preferredLocation;
	private String desiredJobValue;
	private String anyOtherPreferences;
}
