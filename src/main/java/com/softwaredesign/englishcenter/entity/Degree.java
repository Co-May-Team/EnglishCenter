package com.softwaredesign.englishcenter.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "degree")
public class Degree extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "degree_id")
	private int degreeId;
	
	private String name;
	private String schoolName;
	
	@ManyToMany()
	@JoinTable(name = "employee_degree", joinColumns = {
			@JoinColumn(name = "degree_id") }, inverseJoinColumns = {
					@JoinColumn(name = "employee_id") })
	private List<Employee> employees;
}
