package com.movies.rent.models.service.interfaces;

import java.util.List;

import com.movies.rent.models.entity.Business;
import com.movies.rent.models.entity.Operation;
import com.movies.rent.models.entity.User;

public interface IBusinessService {
	public Business save(Business business);
	public List<Business> findByUserAndOperation(User user, Operation operation);
}
