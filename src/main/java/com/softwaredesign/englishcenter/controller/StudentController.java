package com.softwaredesign.englishcenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softwaredesign.englishcenter.entity.Class;
import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.StudentModel;
import com.softwaredesign.englishcenter.service.ClassService;
import com.softwaredesign.englishcenter.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	ClassService classService;
	
    @PostMapping("/add")
    public String add(@RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String citizenNumber,
            @RequestParam(required = false) Integer classId,
            Model model) {
    	Student student = new Student();
    	student.setFirstName(firstName);
    	student.setMidleName(middleName);
    	student.setLastName(lastName);
    	student.setPhoneNumber(phone);
    	student.setAddress(address);
    	student.setCitizenId(citizenNumber);
    	student.setValidflag(true);
    	if(null !=classId && classId > 0 ) {
    		List<Class> classes = new ArrayList<>();
    		Class classObj = classService.findById(classId);
    		classes.add(classObj);
    		student.setClasses(classes);
    		int studentId = studentService.save(student);
    		//update the number of students for class
    		classObj.setNumberOfstudents(classObj.getNumberOfstudents() + 1);
    		classService.update(classObj, studentId);
    		return "redirect:/class/"+classId;
    	}
    	studentService.save(student);

    	return "redirect:/student";
    }
    
    @PostMapping("/update")
    public String update(@RequestParam String studentId,
    		@RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String citizenNumber,
            @RequestParam(required = false) Integer classId,
            Model model) {
    	Student student = studentService.findById(Integer.valueOf(studentId));
    	student.setFirstName(firstName);
    	student.setMidleName(middleName);
    	student.setLastName(lastName);
    	student.setPhoneNumber(phone);
    	student.setAddress(address);
    	student.setCitizenId(citizenNumber);
    	student.setValidflag(true);
    	if(null !=classId && classId > 0 ) {
    		List<Class> classes = new ArrayList<>();
    		Class classObj = classService.findById(classId);
    		classes.add(classObj);
    		student.setClasses(classes);
    		studentService.save(student);
    		return "redirect:/class/"+classId;
    	}
    	studentService.update(student);

    	return "redirect:/student";
    }
    
    @GetMapping("")
    public String findAll(Model model){
    	List<StudentModel> students = studentService.findAll(0);
    	model.addAttribute("students", students);
    	return "student";
    }
}
