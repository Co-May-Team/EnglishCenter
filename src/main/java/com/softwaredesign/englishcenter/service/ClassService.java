package com.softwaredesign.englishcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaredesign.englishcenter.dao.IClassRepository;
import com.softwaredesign.englishcenter.entity.Class;
import com.softwaredesign.englishcenter.model.ClassModel;

@Service
public class ClassService {

	@Autowired
	IClassRepository classRepository;
	
	public List<ClassModel> findAll() {
		List<ClassModel> classes = classRepository.findAll();
		return classes;
	}
	
	public boolean insert(Class classObj) {
		return classRepository.insert(classObj);
	}
	public boolean update(Class classObj, int studentId) {
		return classRepository.update(classObj, studentId);
	}
	
	public ClassModel findModelById(Integer id) {
		ClassModel classModel = new ClassModel();
		classModel = classRepository.findModelById(id);
		return classModel;
	}
	
	public Class findById(Integer id) {
		Class classObj = new Class();
		classObj = classRepository.findById(id);
		return classObj;
	}
	

}
