package com.softwaredesign.englishcenter.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softwaredesign.englishcenter.entity.Course;
import com.softwaredesign.englishcenter.entity.Schedule;
import com.softwaredesign.englishcenter.model.CourseModel;
import com.softwaredesign.englishcenter.service.CourseService;
import com.softwaredesign.englishcenter.service.ScheduleService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	ScheduleService scheduleService;

	@GetMapping
	public String findAll(Model model) {

		List<CourseModel> courseModels = courseService.findAll();

		model.addAttribute("courses", courseModels);

		return "course.html";
	}
	
	@PostMapping("/insert")
	public String insert(@RequestParam String nameCourse,
							@RequestParam String startDate,
							@RequestParam String endDate) {

		try {
	        String formatPattern = "yyyy-MM-dd";
	        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
			Course course = new Course();
			course.setName(nameCourse);
			Schedule schedule = new Schedule();
			schedule.setCourse(course);
			
			java.util.Date startDateTemp = sdf.parse(startDate);
			Date sqlStartDate = new Date(startDateTemp.getTime());
			schedule.setStartDate(sqlStartDate);
			
			java.util.Date endDateTemp = sdf.parse(endDate);
			Date sqlEndDate = new Date(endDateTemp.getTime());
			schedule.setEndDate(sqlEndDate);
			schedule.setValidflag(true);
			schedule.setWeekday(0);
			course.setSchedule(schedule);

			
			courseService.insert(course);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "redirect:/course";
		
	}
	
	@PostMapping("/update")
	public String update(	@RequestParam Integer courseIdUpdate,
							@RequestParam String nameUpdate,
							@RequestParam String startDateUpdate,
							@RequestParam String endDateUpdate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Course course = courseService.findById(Integer.valueOf(courseIdUpdate));
			course.setName(nameUpdate);
			Schedule schedule = scheduleService.findByid(course.getSchedule().getScheduleId());
			java.util.Date startDate = dateFormat.parse(startDateUpdate);
			Date sqlStartDate = new java.sql.Date(startDate.getTime());
			schedule.setStartDate(sqlStartDate);
			java.util.Date endDate = dateFormat.parse(endDateUpdate);
			java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
			schedule.setEndDate(sqlEndDate);
			course.setSchedule(schedule);
			courseService.update(course);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/course";
	}
	
}
