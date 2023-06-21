package com.softwaredesign.englishcenter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel {
	Integer id;
	String fullname;
	String address;
	String phoneNumber;
	String citizenId;
	private boolean validflag;
}
