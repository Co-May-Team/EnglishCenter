package com.softwaredesign.englishcenter.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "employee")
public class Employee {
	
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "midlename")
	private String midleName;
	
	@Column(name = "lastname")
	private String lastName;
	
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private boolean validflag;
	
	private BigDecimal income;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToMany()
	@JoinTable(name = "employee_degree", joinColumns = {
			@JoinColumn(name = "employee_id") }, inverseJoinColumns = {
					@JoinColumn(name = "degree_id") })
	private List<Degree> degrees;
	
	@OneToMany
	@JoinColumn(name = "class_id")
	private List<Class> classes;
}
