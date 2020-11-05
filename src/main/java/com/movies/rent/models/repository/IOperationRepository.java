package com.movies.rent.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.movies.rent.models.entity.Operation;

public interface IOperationRepository extends CrudRepository<Operation, Long>{

}
