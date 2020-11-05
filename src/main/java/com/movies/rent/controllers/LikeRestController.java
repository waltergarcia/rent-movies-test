package com.movies.rent.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.rent.models.dto.LikeDto;
import com.movies.rent.models.entity.Like;
import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.entity.User;
import com.movies.rent.models.service.interfaces.ILikeService;
import com.movies.rent.models.service.interfaces.IMovieService;
import com.movies.rent.models.service.interfaces.IUserService;
import com.movies.rent.utilities.constants.ApiRestConstants;
import com.movies.rent.utilities.constants.Messages;

/**
 * Controller LikeRestController
 * @date 03/11/2020
 * @author Walter Garcia
 */
@CrossOrigin(origins = { ApiRestConstants.ANGULAR_CORS })
@RestController
@RequestMapping(ApiRestConstants.ENDPOINT_API_MOVIES)
public class LikeRestController {
	
	private final Logger logger = LoggerFactory.getLogger(LikeRestController.class);
	private String logMsg;
	
	@Autowired
	private ILikeService likeService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired 
	private IMovieService movieService;
	
	/**
	 * Controller to save a new like
	 * @param like object to save
	 * @return like object saved
	 */
	@PostMapping(ApiRestConstants.SAVE_LIKE)
	@PreAuthorize(ApiRestConstants.IS_ROLE_USER_OR_ROLE_ADMIN)
	public ResponseEntity<?> save(@Valid @RequestBody LikeDto likeDto){
		Map<String, Object> response = new HashMap<>();
		Like like;
		
		logger.info(Messages.LOG_LIKE_MOVIES);
		
		//Check if user exists in database
		Long idUser = likeDto.getIdUser();
		User user = userService.findById(idUser);
		if(user == null) {
			logMsg = String.format(Messages.USER_NOT_FOUND_BY_ID, Long.toString(idUser));
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Check if movie exists in database
		Long idMovie = likeDto.getIdMovie();
		Movie movie = movieService.findById(idMovie);
		if(movie == null) {
			logMsg = String.format(Messages.MOVIE_NOT_FOUND_BY_ID, Long.toString(idMovie));
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			like = new Like();			
			like.setUser(user);
			like.setMovie(movie);			
			likeService.save(like);
			logger.info(String.format(Messages.LOG_LIKED_MOVIES, movie.getTitle(), user.getUsername()));
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_SAVING_LIKE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Messages.MESSAGE, Messages.LIKE_SAVED_SUCCESSFUL);
		response.put(Messages.LIKE, like);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
