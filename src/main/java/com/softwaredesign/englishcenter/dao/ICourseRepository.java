package com.softwaredesign.englishcenter.dao;

import java.util.List;

import com.softwaredesign.englishcenter.entity.Course;
import com.softwaredesign.englishcenter.model.CourseModel;

public interface ICourseRepository {
	List<CourseModel> findAll();
	boolean insert(Course course);
	Course findById(Integer id);
	boolean update(Course course);
}
