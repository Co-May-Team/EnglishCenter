package com.softwaredesign.englishcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaredesign.englishcenter.dao.ICourseRepository;
import com.softwaredesign.englishcenter.entity.Course;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.model.ScheduleModel;

@Service
public class CourseService {
	
	@Autowired
	ICourseRepository courseRepository;
	
	public List<CourseModel> findAll(){
		List<CourseModel> coursesModel = courseRepository.findAll(); 
		return coursesModel;
	}
	public boolean insert(Course course) {
		boolean result = courseRepository.insert(course);
		return result;
	}
	public Course findById(Integer id) {
		return courseRepository.findById(id);
	}
	public CourseModel toOriginModel(Course course) {
		CourseModel courseModel = new CourseModel();
		courseModel.setCourseId(course.getCourseId());
		courseModel.setName(course.getName());
		ScheduleModel scheduleModel = new ScheduleModel();
		scheduleModel.setScheduleId(course.getSchedule().getScheduleId());
		scheduleModel.setStartDate(course.getSchedule().getStartDate().toString());
		scheduleModel.setEndDate(course.getSchedule().getEndDate().toString());
		courseModel.setScheduleModel(scheduleModel);
		return courseModel;
	}
	public boolean update(Course course) {
		return courseRepository.update(course);
	}
}
