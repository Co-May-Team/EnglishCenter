package com.softwaredesign.englishcenter.dao;

import java.util.List;

import com.softwaredesign.englishcenter.entity.Class;
import com.softwaredesign.englishcenter.model.ClassModel;

public interface IClassRepository {
	List<ClassModel> findAll();

	boolean insert(Class classObj);

	ClassModel findModelById(Integer id);

	Class findById(Integer id);

	boolean update(Class classObj);
}
