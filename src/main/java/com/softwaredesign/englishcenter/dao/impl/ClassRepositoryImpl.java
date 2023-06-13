package com.softwaredesign.englishcenter.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softwaredesign.englishcenter.dao.IClassRepository;
import com.softwaredesign.englishcenter.dao.IStudentRepository;
import com.softwaredesign.englishcenter.entity.Class;
import com.softwaredesign.englishcenter.entity.Payment;
import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.ClassModel;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.EmployeeModel;
import com.softwaredesign.englishcenter.model.StudentModel;

import jakarta.persistence.TypedQuery;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ClassRepositoryImpl implements IClassRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRepositoryImpl.class);
	
	@Autowired
	IStudentRepository studentRepository;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ClassModel> findAll() {
		List<ClassModel> classesModel = null;
		List<Class> classes = null;
		StringBuilder hql = new StringBuilder("FROM class AS cl WHERE cl.validflag = '1' ");
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Class> query = session.createQuery(hql.toString(), Class.class);
			classes = query.getResultList();
			classesModel = new ArrayList<>();
			for (Class classItem : classes) {
				ClassModel classModel = new ClassModel();
				classModel.setClassId(classItem.getClassId());
				classModel.setName(classItem.getName());
				classModel.setNumberOfstudents(classItem.getNumberOfstudents());
				classModel.setMaximumSize(classItem.getMaxiumSize());

				CourseModel courseModel = new CourseModel();
				courseModel.setCourseId(classItem.getCourse().getCourseId());
				courseModel.setName(classItem.getCourse().getName());
				classModel.setCourse(courseModel);

				EmployeeModel employeeModel = new EmployeeModel();
				employeeModel.setEmployeeId(classItem.getEmployee().getEmployeeId());
				StringBuilder fullname = new StringBuilder(classItem.getEmployee().getLastName() + " ");
				fullname.append(classItem.getEmployee().getMidleName() + " ");
				fullname.append(classItem.getEmployee().getFirstName());
				employeeModel.setFullname(fullname.toString());
				employeeModel.setAddress(classItem.getEmployee().getAddress());
				employeeModel.setPhoneNumber(classItem.getEmployee().getPhoneNumber());
				employeeModel.setIncome(classItem.getEmployee().getIncome());
				classModel.setEmployee(employeeModel);
				List<StudentModel> studentModels = new ArrayList<>();
				for (Student student : classItem.getStudents()) {
					StudentModel studentModel = new StudentModel();
					studentModel.setId(student.getStudentId());
					studentModel.setFullname(
							student.getLastName() + " " + student.getMidleName() + " " + student.getFirstName());
					studentModel.setAddress(student.getAddress());
					studentModel.setCitizenId(student.getCitizenId());
					studentModel.setPhoneNumber(student.getPhoneNumber());
					studentModels.add(studentModel);
				}
				classModel.setStudents(studentModels);
				classesModel.add(classModel);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return classesModel;
	}

	@Override
	public boolean insert(Class classObj) {
		boolean result = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(classObj);
			result = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean update(Class classObj, int studentId) {
		boolean result = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(classObj);
			if(studentId != -1) {
				Payment payment = new Payment();
				payment.setAmount(classObj.getTuitionFee().doubleValue());
				payment.setClassObject(classObj);
				payment.setStatus(false);
				Student student = studentRepository.findById(studentId);
				payment.setStudent(student);
				LocalDate localDate = LocalDate.now();
				payment.setPaymentDate(Date.valueOf(localDate));
				session.merge(payment);
			}
			result = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ClassModel findModelById(Integer id) {
		ClassModel classModel = null;
		Class classObj = null;
		StringBuilder hql = new StringBuilder("FROM class AS cl WHERE cl.validflag = '1' and cl.classId = :id ");
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Class> query = session.createQuery(hql.toString(), Class.class);
			query.setParameter("id", id);
			classObj = query.getSingleResult();
			classModel = new ClassModel();

			classModel.setClassId(classObj.getClassId());
			classModel.setName(classObj.getName());
			classModel.setNumberOfstudents(classObj.getNumberOfstudents());
			classModel.setMaximumSize(classObj.getMaxiumSize());
			classModel.setTuitionFee(classObj.getTuitionFee().doubleValue());

			CourseModel courseModel = new CourseModel();
			courseModel.setCourseId(classObj.getCourse().getCourseId());
			courseModel.setName(classObj.getCourse().getName());
			classModel.setCourse(courseModel);

			EmployeeModel employeeModel = new EmployeeModel();
			employeeModel.setEmployeeId(classObj.getEmployee().getEmployeeId());
			StringBuilder fullname = new StringBuilder(classObj.getEmployee().getLastName() + " ");
			fullname.append(classObj.getEmployee().getMidleName() + " ");
			fullname.append(classObj.getEmployee().getFirstName());
			employeeModel.setFullname(fullname.toString());
			employeeModel.setAddress(classObj.getEmployee().getAddress());
			employeeModel.setPhoneNumber(classObj.getEmployee().getPhoneNumber());
			employeeModel.setIncome(classObj.getEmployee().getIncome());
			classModel.setEmployee(employeeModel);
			List<StudentModel> studentModels = new ArrayList<>();
			for (Student student : classObj.getStudents()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(student.getStudentId());
				studentModel.setFullname(
						student.getLastName() + " " + student.getMidleName() + " " + student.getFirstName());
				studentModel.setAddress(student.getAddress());
				studentModel.setCitizenId(student.getCitizenId());
				studentModel.setPhoneNumber(student.getPhoneNumber());
				studentModels.add(studentModel);
			}
			classModel.setStudents(studentModels);


		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return classModel;
	}

	@Override
	public Class findById(Integer id) {
		Class classObj = null;
		StringBuilder hql = new StringBuilder("FROM class AS cl WHERE cl.validflag = '1' and cl.classId = :id ");
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Class> query = session.createQuery(hql.toString(), Class.class);
			query.setParameter("id", id);
			classObj = query.getSingleResult();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return classObj;
	}

}
