package com.movies.rent.models.service.interfaces;

import java.util.List;

import com.movies.rent.models.entity.User;

public interface IUserService {
	List<User> findAll();
	User findByUsername(String username);
	User findById(Long id);
	User save(User user);
	void delete(Long id);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
