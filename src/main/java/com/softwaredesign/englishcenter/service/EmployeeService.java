package com.softwaredesign.englishcenter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.softwaredesign.englishcenter.dao.IEmployeeRepository;
import com.softwaredesign.englishcenter.entity.Employee;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.EmployeeOriginModel;
import com.softwaredesign.englishcenter.model.RoleModel;
@Service
public class EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	IEmployeeRepository employeeRepository;
	
	public List<EmployeeModel> findAllByRoleId(Integer roleId) {
		return employeeRepository.findAllByRoleId(roleId);
	}
	public Employee findById(Integer id) {
		return employeeRepository.findById(id);
	}
	
	public List<EmployeeModel> findAll() {
		return employeeRepository.findAll();
	}
	public int update(Employee employee) {
		return employeeRepository.update(employee);
	}
	public EmployeeOriginModel toOriginModel(Employee employee) {
		EmployeeOriginModel employeeOriginModel = new EmployeeOriginModel();
		employeeOriginModel.setId(employee.getEmployeeId());
		employeeOriginModel.setLastname(employee.getLastName());
		employeeOriginModel.setMiddlename(employee.getMidleName());
		employeeOriginModel.setFirstname(employee.getFirstName());
		employeeOriginModel.setPhoneNumber(employee.getPhoneNumber());
		employeeOriginModel.setAddress(employee.getAddress());
		RoleModel roleModel = new RoleModel();
		roleModel.setRoleId(employee.getRole().getRoleId());
		roleModel.setName(employee.getRole().getName());
		employeeOriginModel.setRoleModel(roleModel);
    	return employeeOriginModel;
	}
	public int save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<EmployeeModel> findAllEmployees(String json){
		List<EmployeeModel> employeeModels = null;
		JsonMapper jsonMapper = new JsonMapper();
		JsonNode jsonObjectPost;
		try {
			jsonObjectPost = jsonMapper.readTree(json);
			String name = jsonObjectPost.get("name") != null ? jsonObjectPost.get("name").asText() : "";
			employeeModels = employeeRepository.findAll(name);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return employeeModels;
	}
}
