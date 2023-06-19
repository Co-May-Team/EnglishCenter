package com.softwaredesign.englishcenter.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModel {
	private int courseId;
	private String name;	
	private String nameForDropdown;
	private List<ClassModel> classModels;
	private ScheduleModel scheduleModel;
	
}
