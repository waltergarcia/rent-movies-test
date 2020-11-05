package com.movies.rent.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.movies.rent.models.entity.Business;
import com.movies.rent.models.entity.Operation;
import com.movies.rent.models.entity.User;

public interface IBusinessRepository extends CrudRepository<Business, Long>{
	List<Business> findByUserAndOperation(User user, Operation operation);
}
