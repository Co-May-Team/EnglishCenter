package com.softwaredesign.englishcenter.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassModel {
	private int classId;
	private String name;
	private int numberOfstudents;
	private int maximumSize;
	private CourseModel course;
	private EmployeeModel employee;
	private double tuitionFee;
	private List<StudentModel> students;
}
