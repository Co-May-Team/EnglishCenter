package com.softwaredesign.englishcenter.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softwaredesign.englishcenter.entity.Class;
import com.softwaredesign.englishcenter.entity.Course;
import com.softwaredesign.englishcenter.entity.Employee;
import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.ClassModel;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.StudentModel;
import com.softwaredesign.englishcenter.model.StudentOriginModel;
import com.softwaredesign.englishcenter.service.ClassService;
import com.softwaredesign.englishcenter.service.CourseService;
import com.softwaredesign.englishcenter.service.EmployeeService;
import com.softwaredesign.englishcenter.service.PaymentService;
import com.softwaredesign.englishcenter.service.StudentService;
import com.softwaredesign.englishcenter.ultils.ComonUltil;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	ClassService classService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping
	public String findAll(Model model) {
		
		List<ClassModel> classes = classService.findAll();
		model.addAttribute("classes", classes);
		List<EmployeeModel> employeeModels = employeeService.findAllByRoleId(2);
		model.addAttribute("employees", employeeModels);
		List<CourseModel> courseModels = courseService.findAll();
		model.addAttribute("courses",courseModels);
    	List<StudentModel> students = studentService.findAll(0);
    	model.addAttribute("students", students);
		return "class.html";
	}
	@PostMapping("/insert")
	public String insert(@RequestParam String nameClass,
							@RequestParam String employeeId,
							@RequestParam String tuitionFee,
							@RequestParam String maximumSize,
							@RequestParam String courseId) {
		try {
			Employee employee = employeeService.findById(Integer.valueOf(employeeId));
			Course course = courseService.findById(Integer.valueOf(courseId));
			Class classObj = new Class();
			classObj.setCourse(course);
			classObj.setEmployee(employee);
			classObj.setName(nameClass);
			classObj.setTuitionFee(new BigDecimal(tuitionFee));
			classObj.setMaxiumSize(Integer.valueOf(maximumSize));
			classObj.setValidflag(true);
			classService.insert(classObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/class";
	}
	
	@GetMapping("/{id}")
	public String findClassById(Model model, @PathVariable Integer id) {
		
		ClassModel classObj = classService.findModelById(id);
		model.addAttribute("class", classObj);
		List<StudentModel> students = studentService.findAllStudentsExceptClassId(id,"");
		model.addAttribute("students", students);
		List<EmployeeModel> employeeModels = employeeService.findAllByRoleId(2);
		model.addAttribute("employees", employeeModels);
		List<CourseModel> courseModels = courseService.findAll();
		model.addAttribute("courses",courseModels);
		return "classDetail.html";
	}
	
	@PostMapping("/addStudentForClass")
	@ResponseBody
	public StudentModel addByClassId(@RequestParam Integer studentId, 
			@RequestParam Integer classId) {
		Student student = studentService.findById(studentId);
		Class classObj = classService.findById(classId);
		if(classObj.getNumberOfstudents() == classObj.getMaxiumSize()) {
			return null;
		}
		List<Student> students = classObj.getStudents();
		students.add(student);
		classObj.setStudents(students);
		classObj.setNumberOfstudents(classObj.getNumberOfstudents() + 1);
		if(classService.update(classObj,studentId)) {
			return ComonUltil.toStudentModel(student);
		}else {
			return null;
		}
	}
	
	@PostMapping("/update")
	public String update(	@RequestParam Integer classId,
							@RequestParam String nameClass,
							@RequestParam String employeeId,
							@RequestParam String tuitionFee,
							@RequestParam String maximumSize,
							@RequestParam String courseId) {
		try {
			Employee employee = employeeService.findById(Integer.valueOf(employeeId));
			Course course = courseService.findById(Integer.valueOf(courseId));
			Class classObj = classService.findById(classId);
			
			classObj.setCourse(course);
			classObj.setEmployee(employee);
			classObj.setName(nameClass);
			classObj.setTuitionFee(new BigDecimal(tuitionFee));
			classObj.setMaxiumSize(Integer.valueOf(maximumSize));
			classObj.setValidflag(true);
			classService.update(classObj, -1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/class/" + classId;
	}
	@PostMapping("/deleteStudent")
	@ResponseBody
	public StudentOriginModel delete(@RequestParam Integer studentId, 
			@RequestParam Integer classId) {
			studentService.removeStudentInClass(studentId, classId);
			Class classObj = classService.findById(classId);
			classObj.setNumberOfstudents(classObj.getNumberOfstudents() - 1);
			classService.update(classObj, -1);
			Student student = studentService.findById(studentId);
			
		return studentService.toOriginModel(student);

	}
	
	
}
