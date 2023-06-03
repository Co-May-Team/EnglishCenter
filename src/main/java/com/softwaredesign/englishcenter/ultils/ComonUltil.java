package com.softwaredesign.englishcenter.ultils;

import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.StudentModel;

public class ComonUltil {
	public static StudentModel toStudentModel(Student student) {
		StudentModel studentModel = new StudentModel();
		StringBuilder fullname = new StringBuilder(student.getLastName() + " ");
		fullname.append(student.getMidleName() + " ");
		fullname.append(student.getFirstName());
		studentModel.setFullname(fullname.toString());
		studentModel.setId(student.getStudentId());
		studentModel.setCitizenId(student.getCitizenId());
		studentModel.setAddress(student.getAddress());
		studentModel.setPhoneNumber(student.getPhoneNumber());
		return studentModel;
	}
}
