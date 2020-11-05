package com.movies.rent.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.movies.rent.models.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
