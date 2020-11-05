package com.movies.rent.models.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.movies.rent.models.entity.Movie;

public interface IMovieService {
	public List<Movie> findAll();
	Page<Movie> findAll(Pageable pageable);
	public Movie findById(Long id);
	public List<Movie> findByTitle(String title);
	public List<Movie> findByAvailabilityTrue();
	public List<Movie> findByAvailabilityFalse();
	
	public Movie save(Movie movie);
	public void delete(Long id);
}