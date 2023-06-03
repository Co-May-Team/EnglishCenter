package com.softwaredesign.englishcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softwaredesign.englishcenter.entity.Employee;
import com.softwaredesign.englishcenter.entity.Role;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.RoleModel;
import com.softwaredesign.englishcenter.service.EmployeeService;
import com.softwaredesign.englishcenter.service.RoleService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	RoleService roleService;

	@GetMapping
	public String findAll(Model model) {

		List<EmployeeModel> employeeModels = employeeService.findAll();
		List<RoleModel> roleModels = roleService.findAll();
		model.addAttribute("employees", employeeModels);
		model.addAttribute("roles",roleModels);

		return "employee.html";
	}

	@PostMapping("/update")
	public String update(@RequestParam String employeeId, @RequestParam String firstName,
			@RequestParam String middleName, @RequestParam String lastName, @RequestParam String address,
			@RequestParam String phone, @RequestParam(required = false) Integer classId, 
			@RequestParam String roleId,Model model) {
		Employee employee = employeeService.findById(Integer.valueOf(employeeId));
		employee.setFirstName(firstName);
		employee.setMidleName(middleName);
		employee.setLastName(lastName);
		employee.setPhoneNumber(phone);
		employee.setAddress(address);
		employee.setValidflag(true);
		Role role = roleService.findById(Integer.valueOf(roleId));
		employee.setRole(role);
		employeeService.update(employee);

		return "redirect:/employee";
	}
	
	@PostMapping("/add")
    public String add(@RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam(required = false) Integer roleId,
            Model model) {
    	Employee employee = new Employee();
    	employee.setFirstName(firstName);
    	employee.setMidleName(middleName);
    	employee.setLastName(lastName);
    	employee.setPhoneNumber(phone);
    	employee.setAddress(address);
    	employee.setValidflag(true);
    	Role role = roleService.findById(roleId);
    	employee.setRole(role);
    	employeeService.save(employee);

    	return "redirect:/employee";
    }

}
