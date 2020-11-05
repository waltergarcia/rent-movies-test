package com.movies.rent.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.rent.models.entity.Operation;
import com.movies.rent.models.repository.IOperationRepository;
import com.movies.rent.models.service.interfaces.IOperationService;

@Service
public class IOperationServiceImpl implements IOperationService {

	@Autowired
	private IOperationRepository operationRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Operation findById(Long id) {
		return operationRepository.findById(id).orElse(null);
	}

}
