package com.softwaredesign.englishcenter.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "class")
public class Class extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private int classId;
	
	private String name;
	
	private boolean validflag;
	@Column(name = "tuition_fee")
	private BigDecimal tuitionFee;
	@Column(name = "number_of_students")
	private int numberOfstudents;
	
	@Column(name = "maxium_size")
	private int maxiumSize;
	
	@OneToMany
	@JoinColumn(name = "class_id")
	private List<Schedule> schedules;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_class", joinColumns = {
			@JoinColumn(name = "class_id") }, inverseJoinColumns = {
					@JoinColumn(name = "student_id") })
	private List<Student> students = new ArrayList<>();
	
	@OneToMany
	@JoinColumn(name = "class_id")
	private List<Payment> payments;
	
	@OneToMany
	@JoinColumn(name = "class_id")
	private List<Registration> registrations;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
