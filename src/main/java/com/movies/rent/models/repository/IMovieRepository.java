package com.movies.rent.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movies.rent.models.entity.Movie;
import com.movies.rent.utilities.constants.CustomQueries;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
	@Query(CustomQueries.SELECT_MOVIE_BY_TITLE)
	public List<Movie> findByTitle(String title);

	public List<Movie> findByAvailabilityTrue();
	public List<Movie> findByAvailabilityFalse();
}
