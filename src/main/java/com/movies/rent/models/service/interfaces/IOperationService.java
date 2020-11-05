package com.movies.rent.models.service.interfaces;

import com.movies.rent.models.entity.Operation;

public interface IOperationService {
	public Operation findById(Long id);
}
