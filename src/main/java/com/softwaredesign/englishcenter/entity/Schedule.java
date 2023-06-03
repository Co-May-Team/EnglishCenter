package com.softwaredesign.englishcenter.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "schedule")
public class Schedule extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private int scheduleId;
	
	@OneToOne
	@JoinColumn(name = "class_id")
	private Class classObject;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	private int weekday;
	@Column(name = "start_time")
	private Time startTime;
	@Column(name = "end_time")
	private Time endTime;
	
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	
	
	
	private boolean validflag;
	
}
