package com.softwaredesign.englishcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softwaredesign.englishcenter.entity.Course;
import com.softwaredesign.englishcenter.entity.Employee;
import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.EmployeeOriginModel;
import com.softwaredesign.englishcenter.model.StudentModel;
import com.softwaredesign.englishcenter.model.StudentOriginModel;
import com.softwaredesign.englishcenter.service.CourseService;
import com.softwaredesign.englishcenter.service.EmployeeService;
import com.softwaredesign.englishcenter.service.StudentService;

@Controller
@RequestMapping("/api")
public class APIController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CourseService courseService;
	
    @PostMapping("/findAllStudent")
    @ResponseBody
    public List<StudentModel> findAllStudent(@RequestBody String json){
    	List<StudentModel> students = studentService.findAllStudents(json);
    	return students;
    }
    
    @PostMapping("/findAllEmployee")
    @ResponseBody
    public List<EmployeeModel> findAllEmployee(@RequestBody String json){
    	List<EmployeeModel> employees = employeeService.findAllStudents(json);
    	return employees;
    }
    
    @PostMapping("/findAllStudentExceptClassId")
    @ResponseBody
    public List<StudentModel> findAllStudentsExceptClassId(@RequestBody String json){
    	List<StudentModel> students = studentService.findAllStudentsExceptClassId(json);
    	return students;
    }
    
    @GetMapping("/findStudentById/{id}")
    @ResponseBody
    public StudentOriginModel findStudentById(@PathVariable Integer id){
    	Student student = studentService.findById(id);
    	StudentOriginModel studentOriginModel = studentService.toOriginModel(student);
    	return studentOriginModel;
    }
    
    @GetMapping("/findEmployeeById/{id}")
    @ResponseBody
    public EmployeeOriginModel findEmployeeById(@PathVariable Integer id){
    	Employee employee = employeeService.findById(id);
    	EmployeeOriginModel employeeOriginModel = employeeService.toOriginModel(employee);
    	return employeeOriginModel;
    }
    
    @GetMapping("/findCourseById/{id}")
    @ResponseBody
    public CourseModel findCourseById(@PathVariable Integer id){
    	Course course = courseService.findById(id);
    	CourseModel courseModel = courseService.toOriginModel(course);
    	return courseModel;
    }
     
    
}
