package com.movies.rent.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.rent.models.entity.Role;
import com.movies.rent.models.repository.IRoleRepository;
import com.movies.rent.models.service.interfaces.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Role findByName(String name) {
		return roleRepository.findByName(name).orElse(null);
	}
}
