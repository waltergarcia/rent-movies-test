package com.movies.rent.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.rent.models.entity.Like;
import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.entity.User;
import com.movies.rent.models.repository.ILikeRepository;
import com.movies.rent.models.service.interfaces.ILikeService;

@Service
public class ILikeServiceImpl implements ILikeService{

	@Autowired
	private ILikeRepository likeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Like> findByUser(User user) {
		return likeRepository.findByUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Like> findByMovie(Movie movie) {
		return likeRepository.findByMovie(movie);
	}
	
	@Override
	@Transactional
	public Like save(Like like) {
		return likeRepository.save(like);
	}
}
