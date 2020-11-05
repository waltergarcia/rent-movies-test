package com.movies.rent.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movies.rent.models.dto.RentedMovies;
import com.movies.rent.models.entity.Business;
import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.entity.Operation;
import com.movies.rent.models.entity.User;
import com.movies.rent.models.service.interfaces.IBusinessService;
import com.movies.rent.models.service.interfaces.IMovieService;
import com.movies.rent.models.service.interfaces.IOperationService;
import com.movies.rent.models.service.interfaces.IUploadImageService;
import com.movies.rent.models.service.interfaces.IUserService;
import com.movies.rent.utilities.constants.ApiRestConstants;
import com.movies.rent.utilities.constants.Messages;
import com.movies.rent.utilities.helpers.HelperMethods;

/**
 * Controller MovieRestController
 * @date 31/10/2020
 * @author Walter Garcia
 */
@CrossOrigin(origins = { ApiRestConstants.ANGULAR_CORS })
@RestController
@RequestMapping(ApiRestConstants.ENDPOINT_API_MOVIES)
public class MovieRestController {

	private final Logger logger = LoggerFactory.getLogger(MovieRestController.class);
	private String logMsg;
	
	@Autowired
	private IMovieService movieService;
	
	@Autowired
	private IUploadImageService uploadImageService;
	
	@Autowired
	private IOperationService operationService;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired 
	private IUserService userService;
	
	/**
	 * Controller to retrieve all movies saved in database
	 */
	@GetMapping(ApiRestConstants.GET_ALL_MOVIES)
	public List<Movie> getdAll(){
		List<Movie> movies = movieService.findAll();		
		logger.info(String.format(Messages.LOG_GET_ALL_MOVIES, movies.size()));
		
		return movies;
	}

	/**
	 * Controller to retrieve all movies by page
	 * @param page : number of page
	 * @return page with movie objects
	 */
	@GetMapping(ApiRestConstants.GET_ALL_MOVIES_PAGE)
	public Page<Movie> getdAllPaginator(@PathVariable Integer page){
		logger.info(String.format(Messages.LOG_GET_PAGE_MOVIES, page));		
		Pageable pageable = PageRequest.of(page, 4);
		
		return movieService.findAll(pageable);
	}
	
	/**
	 * Controller to retrieve a movie object by id
	 * @param id of the movie object
	 * @return movie object or message with error
	 */
	@GetMapping(ApiRestConstants.GET_MOVIE_BY_ID)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Movie movie;
		
		logger.info(String.format(Messages.LOG_GET_MOVIE_BY_ID, id));
		
		try {
			 movie = movieService.findById(id);
			 
			 if(movie == null) {
				 logMsg = String.format(Messages.MOVIE_NOT_FOUND_BY_ID, Long.toString(id));
				 logger.info(logMsg);
				 response.put(Messages.MESSAGE, logMsg);
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			 }
			 
			 return new ResponseEntity<Movie>(movie, HttpStatus.OK);
			 
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_GETTING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller to retrieve a movie object by title
	 * @param name of the movie object
	 * @return list of movies object or message with error
	 */
	@GetMapping(ApiRestConstants.GET_MOVIE_BY_TITLE)
	public ResponseEntity<?> getByTitle(@PathVariable String title) {
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies;
		
		logger.info(String.format(Messages.LOG_GET_MOVIE_BY_TITLE, title));
		
		try {
			 movies = movieService.findByTitle(title.toLowerCase());
			 
			 if(movies.isEmpty()) {
				 logMsg = String.format(Messages.MOVIE_NOT_FOUND_BY_TITLE, title);
				 logger.info(logMsg);
				 response.put(Messages.MESSAGE, logMsg);
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			 }
				
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_GETTING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller to retrieve availability movies
	 * @return available movies
	 */
	@GetMapping(ApiRestConstants.GET_AVAILABILITY_MOVIES)
	public ResponseEntity<?> getAvailability() {
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies;
		
		logger.info(Messages.LOG_AVAILABILITY_MOVIES);
		
		try {
			 movies = movieService.findByAvailabilityTrue();
			 if(movies.isEmpty()) {
				 logMsg = Messages.MOVIE_UNAVAILABILITY;
				 logger.info(logMsg);
				 response.put(Messages.MESSAGE, logMsg);
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
				
			 return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_GETTING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller to retrieve unavailability movies
	 * @return unavailability movies
	 */
	@GetMapping(ApiRestConstants.GET_UNAVAILABILITY_MOVIES)
	public ResponseEntity<?> getUnavailability() {
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies;
		
		logger.info(Messages.LOG_UNAVAILABILITY_MOVIES);
		
		try {
			 movies = movieService.findByAvailabilityFalse();
			 
			 if(movies.isEmpty()) {
				logMsg = Messages.MOVIE_AVAILABILITY;
				logger.info(logMsg);
				response.put(Messages.MESSAGE, logMsg);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
				
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_GETTING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller to retrieve movies rented
	 * @param id user
	 * @return movies rented
	 */
	@GetMapping(ApiRestConstants.GET_RENTED_MOVIES)
	@PreAuthorize(ApiRestConstants.IS_ROLE_USER_OR_ROLE_ADMIN)
	public ResponseEntity<?> getRentedMoviesByUser(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		List<Business> businessRentedMovies;
		
		logger.info(String.format(Messages.LOG_RENTED_MOVIES_BY_USER, id));
		
		//Find Operation, 1 : RENT, 2 : SALE
		Operation operation = operationService.findById(1L);

		//Find User
		User user = userService.findById(id);
		if(user == null) {
			logMsg = String.format(Messages.USER_NOT_FOUND_BY_ID, Long.toString(id));
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Find business object
		businessRentedMovies = businessService.findByUserAndOperation(user, operation);
		
		//Set rented movies
		List<RentedMovies> rentedMovies = businessRentedMovies.stream()
										    .map(m -> new RentedMovies(m.getMovie().getTitle(), 
										    		HelperMethods.dateToString(m.getSinceDate()), 
										    		HelperMethods.dateToString(m.getToDate()), 
										    		HelperMethods.calculateDays(m.getSinceDate(), m.getToDate()) ))
										    .collect(Collectors.toList());

		
		response.put(Messages.RENTED_MOVIES, rentedMovies);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/**
	 * Controller to save a new movie object in database
	 * @param movie object to save
	 * @return movie object saved
	 */
	@PostMapping(ApiRestConstants.SAVE_MOVIE)
	@PreAuthorize(ApiRestConstants.IS_ROLE_ADMIN)
	public ResponseEntity<?> save(@Valid @RequestBody Movie movie) {
		Map<String, Object> response = new HashMap<>();
		Movie saveMovie;
		
		logger.info(Messages.LOG_SAVE_MOVIE);
	
		try {
			saveMovie = movieService.save(movie);
			
			response.put(Messages.MESSAGE, Messages.MOVIE_SAVED_SUCCESSFUL);
			response.put(Messages.MOVIE, saveMovie);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_SAVING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller to update a movie in database
	 * @param updateMovie : new movie object to update in database
	 * @param id of the movie to update
	 * @return the movie object updated
	 */
	@PutMapping(ApiRestConstants.UPDATE_MOVIE)
	@PreAuthorize(ApiRestConstants.IS_ROLE_ADMIN)
	public ResponseEntity<?> update(@Valid @RequestBody Movie updateMovie, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Movie currentMovie = movieService.findById(id);
		Movie movieUpdated;
		
		logger.info(Messages.LOG_UPDATE_MOVIE);
		
		if(currentMovie == null) {
			logMsg = String.format(Messages.ERROR_UPDATING_MOVIE_ID, Long.toString(id));
			logger.info(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			movieUpdated = movieService.save(HelperMethods.updateMovieObj(currentMovie, updateMovie));
			logger.info(Messages.MOVIE_UPDATED_SUCCESSFUL.concat(movieUpdated.toString()));
			
			response.put(Messages.MESSAGE, Messages.MOVIE_UPDATED_SUCCESSFUL);
			response.put(Messages.MOVIE, movieUpdated);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_UPDATING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller to delete a movie in database
	 * @param id of the movie object to delete 
	 */
	@DeleteMapping(ApiRestConstants.DELETE_MOVIE)
	@PreAuthorize(ApiRestConstants.IS_ROLE_ADMIN)
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Movie currentMovie = movieService.findById(id);
		
		logger.info(Messages.LOG_DELETE_MOVIE);
		
		if(currentMovie == null) {
			logMsg = String.format(Messages.ERROR_DELETING_MOVIE_ID, Long.toString(id));
			logger.info(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			movieService.delete(id);
			
			response.put(Messages.MESSAGE, Messages.MOVIE_DELETED_SUCCESSFUL);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_DELETING_MOVIE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	/**
	 * Controller to upload a movie image
	 * @param imageFile movie image path
	 * @param id movie
	 * @return movie object with its image
	 */
	@PostMapping(ApiRestConstants.UPLOAD_MOVIE_IMAGE)
	@PreAuthorize(ApiRestConstants.IS_ROLE_ADMIN)
	public ResponseEntity<?> uploadImage(@RequestParam(ApiRestConstants.REQUEST_PARAM_IMAGE) MultipartFile imageFile, 
												@RequestParam(ApiRestConstants.REQUEST_PARAM_ID) Long id){
		Map<String, Object> response = new HashMap<>();
		Movie movie = movieService.findById(id);
		
		logger.info(Messages.LOG_UPLOAD_IMAGE);
		
		if(!imageFile.isEmpty()) {
			String imageName;
			
			try {
				imageName = uploadImageService.copy(imageFile);
			}catch(IOException e) {
				logMsg = Messages.ERROR_UPLOADING_IMAGE;
				logger.error(logMsg, e);
				response.put(Messages.MESSAGE, logMsg);
				response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String oldImage = movie.getImage();
			
			uploadImageService.delete(oldImage);
			
			movie.setImage(imageName);
			movieService.save(movie);
			
			response.put(Messages.MOVIE, movie);
			response.put(Messages.MESSAGE, String.format(Messages.IMAGE_UPLOAD_SUCCESFUL, imageName));
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/**
	 * Controller to show the movie image
	 * @param imageName saved in upload image
	 * @return movie image
	 */
	@GetMapping(ApiRestConstants.SHOW_MOVIE_IMAGE)
	public ResponseEntity<Resource> showImage(@PathVariable String imageName){
		Map<String, Object> response = new HashMap<>();
		Resource resource = null;
		HttpHeaders headers = new HttpHeaders();
		
		logger.info(Messages.LOG_SHOW_IMAGE);
		
		try {
			resource = uploadImageService.load(imageName);
		}catch(MalformedURLException e) {
			logMsg = Messages.ERROR_SHOWING_IMAGE;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getCause().getMessage()));
		}
		
		headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format(ApiRestConstants.ATTACHMENT_FILE, resource.getFilename()));
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
