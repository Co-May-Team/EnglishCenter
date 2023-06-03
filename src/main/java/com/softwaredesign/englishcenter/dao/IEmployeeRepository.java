package com.softwaredesign.englishcenter.dao;

import java.util.List;

import com.softwaredesign.englishcenter.entity.Employee;
import com.softwaredesign.englishcenter.model.EmployeeModel;

public interface IEmployeeRepository {
	List<EmployeeModel> findAllByRoleId(Integer roleId);
	Employee findById(Integer id);
	List<EmployeeModel> findAll(String name);
	int update(Employee employee);
	int save(Employee employee);
	List<EmployeeModel> findAll();
}
