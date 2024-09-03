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
public class UserPersonalDetails {
	@TableGenerator(allocationSize = 10, initialValue = 0, name = "user_personal_details_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_personal_details_sequence")
	private long userPersonalId;
	private String userHeight;
	private String userWeight;
	private String gotra;
	private String manglik;
	private String maritalStatus;
	private String isPersonDisabled;
	private String userIncome;
	private String isUserStayingAlone;
	private List<String> hobbies;
	private String birthPlace;
	private String complexion;
	private String rashi;
	private String bloodGroup;
	private String bodyType;
}
