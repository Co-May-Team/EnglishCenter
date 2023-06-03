package com.softwaredesign.englishcenter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.softwaredesign.englishcenter.dao.IStudentRepository;
import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.StudentModel;
import com.softwaredesign.englishcenter.model.StudentOriginModel;

@Service
public class StudentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	IStudentRepository studentRepository;
	
	public List<StudentModel> findAllStudents(String json){
		List<StudentModel> studentModels = null;
		JsonMapper jsonMapper = new JsonMapper();
		JsonNode jsonObjectPost;
		try {
			jsonObjectPost = jsonMapper.readTree(json);
			Integer classId = jsonObjectPost.get("classId") != null ? jsonObjectPost.get("classId").asInt() : 0;
			String name = jsonObjectPost.get("name") != null ? jsonObjectPost.get("name").asText() : "";
			studentModels = studentRepository.findAll(classId, name);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return studentModels;
	}
	
	public List<StudentModel> findAll(Integer classId){
		return studentRepository.findAll(classId, "");
	}
	
	public List<StudentModel> findAllStudentsExceptClassId(String json){
		List<StudentModel> studentModels = null;
		JsonMapper jsonMapper = new JsonMapper();
		JsonNode jsonObjectPost;
		try {
			jsonObjectPost = jsonMapper.readTree(json);
			Integer classId = jsonObjectPost.get("classId") != null ? jsonObjectPost.get("classId").asInt() : 0;
			String name = jsonObjectPost.get("name") != null ? jsonObjectPost.get("name").asText() : "";
			studentModels = studentRepository.findAllExceptClassId(classId, name);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return studentModels;
	}
	public List<StudentModel> findAllStudentsExceptClassId(Integer classId, String name){
		List<StudentModel> studentModels = null;
		try {
			studentModels = studentRepository.findAllExceptClassId(classId, name);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return studentModels;
	}
	
	public int save(Student student) {
		return studentRepository.save(student);
	}
	
	public int update(Student student) {
		return studentRepository.update(student);
	}
	
	public Student findById(Integer id) {
		return studentRepository.findById(id);
	}
	public boolean deleteById(Integer id) {
		return studentRepository.deleteById(id);
	}
	public boolean removeStudentInClass(Integer studentId, Integer classId) {
		return studentRepository.removeStudentInClass(studentId, classId);
	}
	
	public StudentOriginModel toOriginModel(Student student) {
    	StudentOriginModel studentOriginModel = new StudentOriginModel();
    	studentOriginModel.setId(student.getStudentId());
    	studentOriginModel.setLastname(student.getLastName());
    	studentOriginModel.setMiddlename(student.getMidleName());
    	studentOriginModel.setFirstname(student.getFirstName());
    	studentOriginModel.setPhoneNumber(student.getPhoneNumber());
    	studentOriginModel.setCitizenId(student.getCitizenId());
    	studentOriginModel.setAddress(student.getAddress());
    	return studentOriginModel;
	}
}
