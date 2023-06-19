package com.softwaredesign.englishcenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softwaredesign.englishcenter.dao.IEmployeeRepository;
import com.softwaredesign.englishcenter.entity.Employee;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.RoleModel;

import jakarta.persistence.TypedQuery;
@Repository
@Transactional(rollbackFor = Exception.class)
public class EmployeeRepositoryImpl implements IEmployeeRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<EmployeeModel> findAllByRoleId(Integer roleId) {
		List<EmployeeModel> employeeModels = null;
		List<Employee> employees = null;
		
		StringBuilder hql = new StringBuilder("FROM employee AS emp WHERE emp.role.roleId= :roleId and emp.validflag = '1' ");
		try {
			Session session = sessionFactory.getCurrentSession();
	        TypedQuery<Employee> query = session.createQuery(hql.toString(), Employee.class);
	        query.setParameter("roleId", roleId);
	        employees = query.getResultList();
	        employeeModels = new ArrayList<>();
	        for(Employee employee : employees) {
	        	EmployeeModel employeeModel = new EmployeeModel();
	        	employeeModel.setEmployeeId(employee.getEmployeeId());
	        	employeeModel.setFullname(employee.getLastName() + " " + employee.getMidleName() + " " + employee.getFirstName());
	        	employeeModel.setAddress(employee.getAddress());
	        	employeeModel.setIncome(employee.getIncome());
	        	employeeModel.setPhoneNumber(employee.getPhoneNumber());
	        	employeeModels.add(employeeModel);
	        }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return employeeModels;
	}
	@Override
	public Employee findById(Integer id) {
		Employee employee = null;
		String hql = "FROM employee AS em WHERE em.employeeId = :employeeId";
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Employee> query = session.createQuery(hql,Employee.class);
			query.setParameter("employeeId", id);
			employee = query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return employee;
	}
	@Override
	public List<EmployeeModel> findAll() {
		List<EmployeeModel> employeeModels = null;
		List<Employee> employees = null;
		
		StringBuilder hql = new StringBuilder("FROM employee AS emp WHERE emp.role.roleId != '1' ");
		try {
			Session session = sessionFactory.getCurrentSession();
	        TypedQuery<Employee> query = session.createQuery(hql.toString(), Employee.class);
	        employees = query.getResultList();
	        employeeModels = new ArrayList<>();
	        for(Employee employee : employees) {
	        	EmployeeModel employeeModel = new EmployeeModel();
	        	employeeModel.setEmployeeId(employee.getEmployeeId());
	        	employeeModel.setFullname(employee.getLastName() + " " + employee.getMidleName() + " " + employee.getFirstName());
	        	employeeModel.setAddress(employee.getAddress());
	        	employeeModel.setIncome(employee.getIncome());
	        	employeeModel.setPhoneNumber(employee.getPhoneNumber());
	        	RoleModel roleModel = new RoleModel();
	        	roleModel.setRoleId(employee.getRole().getRoleId());
	        	roleModel.setName(employee.getRole().getName());
	        	employeeModel.setRole(roleModel);
	        	employeeModel.setValidflag(employee.isValidflag());
	        	employeeModels.add(employeeModel);
	        }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return employeeModels;
	}
	@Override
	public int update(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.merge(employee);
			return 1;
		} catch (Exception e) {
			LOGGER.error("Error has occured at add() ", e);
		}
		return -1;
	}
	
	@Override
	public int save(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.persist(employee);
			return 1;
		} catch (Exception e) {
			LOGGER.error("Error has occured at add() ", e);
		}
		return -1;
	}
	@Override
	public List<EmployeeModel> findAll(String name) {
		List<EmployeeModel> employeeModels = null;
		List<Employee> employees = null;
		
		StringBuilder hql = new StringBuilder("FROM employee AS emp WHERE emp.role.roleId != '1' and emp.validflag = '1' ");
		hql.append(" AND ( emp.firstName LIKE '%" + name);
		hql.append("%' OR emp.midleName LIKE '%" + name);
		hql.append("%' OR emp.lastName LIKE '%" + name);
		hql.append("%')");
		try {
			Session session = sessionFactory.getCurrentSession();
	        TypedQuery<Employee> query = session.createQuery(hql.toString(), Employee.class);
	        employees = query.getResultList();
	        employeeModels = new ArrayList<>();
	        for(Employee employee : employees) {
	        	EmployeeModel employeeModel = new EmployeeModel();
	        	employeeModel.setEmployeeId(employee.getEmployeeId());
	        	employeeModel.setFullname(employee.getLastName() + " " + employee.getMidleName() + " " + employee.getFirstName());
	        	employeeModel.setAddress(employee.getAddress());
	        	employeeModel.setIncome(employee.getIncome());
	        	employeeModel.setPhoneNumber(employee.getPhoneNumber());
	        	RoleModel roleModel = new RoleModel();
	        	roleModel.setRoleId(employee.getRole().getRoleId());
	        	roleModel.setName(employee.getRole().getName());
	        	employeeModel.setRole(roleModel);
	        	employeeModels.add(employeeModel);
	        }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return employeeModels;
	}


}
