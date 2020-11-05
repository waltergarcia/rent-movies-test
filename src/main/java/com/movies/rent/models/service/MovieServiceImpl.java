package com.movies.rent.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.repository.IMovieRepository;
import com.movies.rent.models.service.interfaces.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService{

	@Autowired
	private IMovieRepository movieRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Movie> findAll() {
		return (List<Movie>) movieRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Movie> findAll(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Movie findById(Long id) {
		return movieRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByTitle(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	@Transactional
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByAvailabilityTrue() {
		return movieRepository.findByAvailabilityTrue();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByAvailabilityFalse() {
		return movieRepository.findByAvailabilityFalse();
	}
}
