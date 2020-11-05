package com.movies.rent.models.service.interfaces;

import java.util.List;

import com.movies.rent.models.entity.Like;
import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.entity.User;

public interface ILikeService {
	List<Like> findByUser(User user);
	List<Like> findByMovie(Movie movie);
	Like save(Like like);
}
