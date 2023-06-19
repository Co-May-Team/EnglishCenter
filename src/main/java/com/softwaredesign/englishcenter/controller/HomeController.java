package com.softwaredesign.englishcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.softwaredesign.englishcenter.model.ClassModel;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.StudentModel;
import com.softwaredesign.englishcenter.service.ClassService;
import com.softwaredesign.englishcenter.service.CourseService;
import com.softwaredesign.englishcenter.service.EmployeeService;
import com.softwaredesign.englishcenter.service.StudentService;

@Controller
public class HomeController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ClassService classService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	EmployeeService employeeService;
	
    @GetMapping("/")
    public String home(Model model) {
        List<ClassModel> classes = classService.findAll();
        List<CourseModel> courses = courseService.findAll();
        int studentNum = studentService.findTotalStudent();
        List<EmployeeModel> employeeModels = employeeService.findAll();
        model.addAttribute("employeeModels", employeeModels);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("classes", classes);
        model.addAttribute("courses",courses);
        
        return "index";
    }
    
    @GetMapping("/index")
    public String profile(Model model) {
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
}
