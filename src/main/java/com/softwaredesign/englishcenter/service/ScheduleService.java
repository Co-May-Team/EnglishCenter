package com.softwaredesign.englishcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaredesign.englishcenter.dao.IScheduleRepository;
import com.softwaredesign.englishcenter.entity.Schedule;

@Service
public class ScheduleService {
	
	@Autowired
	IScheduleRepository scheduleRepository;
	
	public Schedule findByid(int id) {
		return scheduleRepository.findById(id);
	}
}
