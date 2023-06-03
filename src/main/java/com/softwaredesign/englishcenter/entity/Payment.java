package com.softwaredesign.englishcenter.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payment")
public class Payment extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name = "payment_id")
	private int paymentId;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name = "class_id")
	private Class classObject;
	private BigDecimal amount;
	
	@Column(name = "payment_date")
	private Date paymentDate;
}
