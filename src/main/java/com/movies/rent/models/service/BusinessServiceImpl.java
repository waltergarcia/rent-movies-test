package com.movies.rent.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.rent.models.entity.Business;
import com.movies.rent.models.entity.Operation;
import com.movies.rent.models.entity.User;
import com.movies.rent.models.repository.IBusinessRepository;
import com.movies.rent.models.service.interfaces.IBusinessService;

@Service
public class BusinessServiceImpl implements IBusinessService {
	
	@Autowired
	private IBusinessRepository businessRepository;

	@Override
	@Transactional
	public Business save(Business business) {
		return businessRepository.save(business);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Business> findByUserAndOperation(User user, Operation operation) {
		return businessRepository.findByUserAndOperation(user, operation);
	}
}
