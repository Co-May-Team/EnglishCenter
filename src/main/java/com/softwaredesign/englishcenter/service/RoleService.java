package com.softwaredesign.englishcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaredesign.englishcenter.dao.IRoleRepository;
import com.softwaredesign.englishcenter.entity.Role;
import com.softwaredesign.englishcenter.model.RoleModel;

@Service
public class RoleService {
	
	@Autowired
	IRoleRepository roleRepository;
	
	public List<RoleModel> findAll(){
		return roleRepository.findAll();
	}
	
	public Role findById(int id) {
		return roleRepository.findById(id);
	}
}
