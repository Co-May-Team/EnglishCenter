package com.softwaredesign.englishcenter.dao;

import com.softwaredesign.englishcenter.entity.Schedule;
import com.softwaredesign.englishcenter.model.ScheduleModel;

public interface IScheduleRepository {
	ScheduleModel findModelById(int id);
	Schedule findById(int id);
}
