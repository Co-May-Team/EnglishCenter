package com.softwaredesign.englishcenter.entity;

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
@Entity(name = "registration")
public class Registration extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration_id")
	private int registrationId;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name = "class_id")
	private Class classObject;
	
	@Column(name = "registration_date")
	private Date registrationDate;
}
