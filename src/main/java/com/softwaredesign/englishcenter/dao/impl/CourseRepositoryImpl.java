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

import com.softwaredesign.englishcenter.dao.ICourseRepository;
import com.softwaredesign.englishcenter.entity.Class;
import com.softwaredesign.englishcenter.entity.Course;
import com.softwaredesign.englishcenter.model.ClassModel;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.ScheduleModel;

import jakarta.persistence.TypedQuery;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CourseRepositoryImpl implements ICourseRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<CourseModel> findAll() {
		List<CourseModel> courseModels = null;
		List<Course> courses = null;
		StringBuilder hql = new StringBuilder("FROM course");
		try {
			Session session = sessionFactory.getCurrentSession();
	        TypedQuery<Course> query = session.createQuery(hql.toString(), Course.class);
	        courses = query.getResultList();
	        courseModels = new ArrayList<CourseModel>();
	        for(Course course : courses) {
	        	CourseModel courseModel = new CourseModel();
	        	courseModel.setCourseId(course.getCourseId());
	        	courseModel.setName(course.getName());
	        	List<ClassModel> classModels = new ArrayList<>();
	        	for(Class classItem : course.getClasses()) {
	        		ClassModel classModel = new ClassModel();
	        		classModel.setClassId(classItem.getClassId());
	        		classModel.setName(classItem.getName());
	        		classModel.setMaximumSize(classItem.getMaxiumSize());
	        		classModel.setNumberOfstudents(classItem.getNumberOfstudents());
	        		EmployeeModel employeeModel = new EmployeeModel();
	        		employeeModel.setEmployeeId(classItem.getEmployee().getEmployeeId());
	        		StringBuilder fullname = new StringBuilder(classItem.getEmployee().getLastName() + " ");
	        		fullname.append(classItem.getEmployee().getMidleName() + " ");
	        		fullname.append(classItem.getEmployee().getFirstName());
	        		employeeModel.setFullname(fullname.toString());
	        		employeeModel.setPhoneNumber(classItem.getEmployee().getPhoneNumber());
	        		employeeModel.setAddress(classItem.getEmployee().getAddress());
	        		employeeModel.setIncome(classItem.getEmployee().getIncome());
	        		classModel.setEmployee(employeeModel);
	        		
	        		classModels.add(classModel);
	        	}
	        	courseModel.setClassModels(classModels);
	        	ScheduleModel scheduleModel = new ScheduleModel();
	        	scheduleModel.setScheduleId(course.getSchedule().getScheduleId());
	        	scheduleModel.setStartDate(course.getSchedule().getStartDate().toString());
	        	scheduleModel.setEndDate(course.getSchedule().getEndDate().toString());
	        	courseModel.setScheduleModel(scheduleModel);
	        	courseModels.add(courseModel);
	        }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return courseModels;
	}

	@Override
	public boolean insert(Course course) {
		boolean result = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(course);
			result = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Course findById(Integer id) {
		Course course = null;
		String hql = "FROM course AS co WHERE co.courseId = :courseId";
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Course> query = session.createQuery(hql,Course.class);
			query.setParameter("courseId", id);
			course = query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return course;
	}

	@Override
	public boolean update(Course course) {
		boolean result = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(course);
			result = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

}
