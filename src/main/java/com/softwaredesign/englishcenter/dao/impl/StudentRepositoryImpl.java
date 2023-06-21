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

import com.softwaredesign.englishcenter.dao.IStudentRepository;
import com.softwaredesign.englishcenter.entity.Student;
import com.softwaredesign.englishcenter.model.StudentModel;
import com.softwaredesign.englishcenter.entity.Class;

import jakarta.persistence.TypedQuery;

@Repository
@Transactional(rollbackFor = Exception.class)
public class StudentRepositoryImpl implements IStudentRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<StudentModel> findAll(Integer classId, String name) {
		List<Student> students = null;
		List<StudentModel> studentModels = new ArrayList<StudentModel>();
		;
		StringBuilder hql = new StringBuilder("FROM student AS st left join st.classes cl WHERE ");
		if (classId > 0) {
			hql.append(" cl.classId = :classId AND ");
		}
		hql.append(" ( st.firstName LIKE '%" + name);
		hql.append("%' OR st.midleName LIKE '%" + name);
		hql.append("%' OR st.lastName LIKE '%" + name);
		hql.append("%')");
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Student> query = session.createQuery(hql.toString(), Student.class);
			if (classId > 0) {
				query.setParameter("classId", classId);
			}
			students = query.getResultList();
			if (students.size() > 0) {
				for (Student student : students) {
					StudentModel studentModel = new StudentModel();
					studentModel.setId(student.getStudentId());
					studentModel.setFullname(
							student.getLastName() + " " + student.getMidleName() + " " + student.getFirstName());
					studentModel.setAddress(student.getAddress());
					studentModel.setPhoneNumber(student.getPhoneNumber());
					studentModel.setCitizenId(student.getCitizenId());
					studentModel.setValidflag(student.isValidflag());
					studentModels.add(studentModel);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return studentModels;
	}

	@Override
	public int save(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.persist(student);
			return student.getStudentId();
		} catch (Exception e) {
			LOGGER.error("Error has occured at add() ", e);
		}
		return -1;
	}

	@Override
	public List<StudentModel> findAllExceptClassId(Integer classId, String name) {
		List<Student> students = null;
		List<StudentModel> studentModels = new ArrayList<StudentModel>();

		StringBuilder hql = new StringBuilder("FROM student AS st left join st.classes cl WHERE st.validflag = 1 ");
		if (classId > 0) {
			hql.append(" AND (cl.classId != :classId OR cl.classId IS NULL) ");
		}
		hql.append(
				" AND st.studentId NOT IN (SELECT st1.studentId FROM student AS st1 left join st1.classes cl1 WHERE st1.validflag = 1");
		if (classId > 0) {
			hql.append(" AND cl1.classId = :classId)");
		} else {
			hql.append(")");
		}
		try {
			Session session = sessionFactory.getCurrentSession();
//	        TypedQuery<Student> query = session.createQuery(hql.toString(), Student.class);
//	        if(classId > 0) {
//	        	query.setParameter("classId", classId);
//	        }
//	        students = query.getResultList();

			students = session.createQuery(hql.toString(), Student.class).setParameter("classId", classId)
					.getResultList();
			if (students.size() > 0) {
				for (Student student : students) {
					StudentModel studentModel = new StudentModel();
					studentModel.setId(student.getStudentId());
					studentModel.setFullname(
							student.getLastName() + " " + student.getMidleName() + " " + student.getFirstName());
					studentModel.setAddress(student.getAddress());
					studentModel.setPhoneNumber(student.getPhoneNumber());
					studentModel.setCitizenId(student.getCitizenId());
					studentModels.add(studentModel);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return studentModels;
	}

	@Override
	public Student findById(Integer id) {
		Student student = null;
		StringBuilder hql = new StringBuilder("FROM student AS st WHERE st.studentId=:id  ");
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Student> query = session.createQuery(hql.toString(), Student.class);
			query.setParameter("id", id);
			student = query.getSingleResult();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return student;
	}

	@Override
	public boolean deleteById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Student student = findById(id);
			session.remove(student);
			return true;
		} catch (Exception e) {
			LOGGER.error("Error has occured at add() ", e);
		}
		return false;
	}

	@Override
	public boolean removeStudentInClass(Integer studentId, Integer classId) {
		Student student = findById(studentId);
		Class targetClass = student.getClasses().stream().filter(c -> c.getClassId() == classId).findFirst()
				.orElseThrow();
		student.getClasses().remove(targetClass);
		update(student);
		return false;
	}
	
	@Override
	public int update(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.merge(student);
			return 1;
		} catch (Exception e) {
			LOGGER.error("Error has occured at add() ", e);
		}
		return -1;
	}
	public int findTotalStudent() {
		List<Student> students = null;
		StringBuilder hql = new StringBuilder("FROM student AS st WHERE st.validflag = 1 ");

		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Student> query = session.createQuery(hql.toString(), Student.class);
			students = query.getResultList();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return students.size();
	}
}
