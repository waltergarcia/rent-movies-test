package com.movies.rent.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.movies.rent.models.entity.Like;
import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.entity.User;

public interface ILikeRepository extends CrudRepository<Like, Long>{
	List<Like> findByUser(User user);
	List<Like> findByMovie(Movie user);
}
