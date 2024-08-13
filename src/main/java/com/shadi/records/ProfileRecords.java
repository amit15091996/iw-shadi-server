package com.shadi.records;

import java.time.LocalDate;

public record ProfileRecords(String mobileNumber, String firstName, String lastName, int age, String gender,
		String langKnown, String religion, String community, LocalDate dob, String residence) {

}
