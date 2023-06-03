package com.softwaredesign.englishcenter.dao;

import java.util.List;

import com.softwaredesign.englishcenter.entity.Role;
import com.softwaredesign.englishcenter.model.RoleModel;

public interface IRoleRepository {
	List<RoleModel >findAll();
	Role findById(int id);
}
