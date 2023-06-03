package com.softwaredesign.englishcenter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOriginModel {
	Integer id;
	String lastname;
	String middlename;
	String firstname;
	String address;
	String phoneNumber;
	String citizenId;
}
