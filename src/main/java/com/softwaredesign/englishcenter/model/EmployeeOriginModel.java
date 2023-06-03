package com.softwaredesign.englishcenter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeOriginModel {
	Integer id;
	String lastname;
	String middlename;
	String firstname;
	String address;
	String phoneNumber;
	RoleModel roleModel;
}
