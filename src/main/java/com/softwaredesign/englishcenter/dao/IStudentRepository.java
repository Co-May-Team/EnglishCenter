package com.softwaredesign.englishcenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.StudentModel;

@Repository
public interface IStudentRepository {
	

	List<StudentModel> findAll(Integer classId, String name);
	int save(Student student);
	List<StudentModel> findAllExceptClassId(Integer classId, String name);
	Student findById(Integer id);
	boolean deleteById(Integer id);
	boolean removeStudentInClass(Integer studentId, Integer classId);
	int update(Student student);
}
