package com.softwaredesign.englishcenter.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
	
	private int paymentId;
	private StudentModel studentModel;
	private ClassModel classModel;
	private Double amount;
	private Date paymentDate;
	private String status;
	
}
