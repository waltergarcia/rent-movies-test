
package com.movies.rent.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.movies.rent.models.entity.User;

public interface IUsersRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
