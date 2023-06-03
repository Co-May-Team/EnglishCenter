package com.softwaredesign.englishcenter.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
	private int employeeId;
	private String fullname;
	private String address;
	private String phoneNumber;
	private BigDecimal income;
	private RoleModel role;
}
