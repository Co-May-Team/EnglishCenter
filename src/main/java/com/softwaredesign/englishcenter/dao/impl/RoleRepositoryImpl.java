package com.softwaredesign.englishcenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softwaredesign.englishcenter.dao.IRoleRepository;
import com.softwaredesign.englishcenter.entity.Role;
import com.softwaredesign.englishcenter.model.RoleModel;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RoleRepositoryImpl implements IRoleRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<RoleModel> findAll() {
		List<RoleModel> roleModels = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		String hql = "FROM role WHERE roleId != '1'";
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Role> query = session.createQuery(hql, Role.class);
			roles = query.getResultList();
			for(Role role : roles) {
				RoleModel model = new RoleModel();
				model.setRoleId(role.getRoleId());
				model.setName(role.getName());
				roleModels.add(model);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roleModels;
	}

	@Override
	public Role findById(int id) {
		Role role = new Role();
		String hql = "FROM role WHERE roleId = :roleId";
		try {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Role> query = session.createQuery(hql, Role.class);
			query.setParameter("roleId", id);
			role = query.getSingleResult();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return role;
	}

}
