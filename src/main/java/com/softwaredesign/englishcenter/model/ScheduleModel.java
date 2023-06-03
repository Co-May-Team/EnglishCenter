package com.softwaredesign.englishcenter.model;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel {
	
	private int scheduleId;

	private ClassModel classObject;

	private CourseModel course;

	private int weekday;

	private Time startTime;

	private Time endTime;

	private String startDate;

	private String endDate;
}
