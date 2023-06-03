package com.softwaredesign.englishcenter.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softwaredesign.englishcenter.dao.IScheduleRepository;
import com.softwaredesign.englishcenter.entity.Schedule;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.ScheduleModel;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ScheduleRepositoryImpl implements IScheduleRepository {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ScheduleRepositoryImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public ScheduleModel findModelById(int id) {
		Schedule schedule = new Schedule();
		ScheduleModel scheduleModel = new ScheduleModel();
		String hql = "FROM schedule WHERE scheduleId = :scheduleId";
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Schedule> query = session.createQuery(hql, Schedule.class);
			query.setParameter("scheduleId", id);
			schedule = query.getSingleResult();
			scheduleModel.setScheduleId(schedule.getScheduleId());
			CourseModel courseModel = new CourseModel();
			courseModel.setCourseId(schedule.getCourse().getCourseId());
			courseModel.setName(schedule.getCourse().getName());
			scheduleModel.setCourse(courseModel);
			scheduleModel.setStartDate(schedule.getStartDate().toString());
			scheduleModel.setEndDate(schedule.getEndDate().toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return scheduleModel;
	}

	@Override
	public Schedule findById(int id) {
		Schedule schedule = new Schedule();
		String hql = "FROM schedule WHERE scheduleId = :scheduleId";
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Schedule> query = session.createQuery(hql, Schedule.class);
			query.setParameter("scheduleId", id);
			schedule = query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return schedule;
	}

}
