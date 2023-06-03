package com.softwaredesign.englishcenter.entity;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class Student extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "midlename")
	private String midleName;
	
	@Column(name = "lastname")
	private String lastName;
	
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "citizen_id")
	private String citizenId;
	
	private boolean validflag;
	
//    @ManyToMany(mappedBy = "students")
//    private List<Class> classes = new ArrayList<>();
    
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "student_class", joinColumns = {
//			@JoinColumn(name = "student_id") }, inverseJoinColumns = {
//					@JoinColumn(name = "class_id") })
//	private List<Class> classes;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_class", joinColumns = {
			@JoinColumn(name = "student_id") }, inverseJoinColumns = {
					@JoinColumn(name = "class_id") })
    private List<Class> classes = new ArrayList<>();
	
	@OneToMany
	@JoinColumn(name = "student_id")
	private List<Payment> payments;
	
	@OneToMany
	@JoinColumn(name = "student_id")
	private List<Registration> registrations;
}
