package com.movies.rent.controllers;

import java.text.ParseException;
import java.util.Date;
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

import com.movies.rent.models.dto.BusinessDto;
import com.movies.rent.models.entity.Business;
import com.movies.rent.models.entity.Movie;
import com.movies.rent.models.entity.Operation;
import com.movies.rent.models.entity.User;
import com.movies.rent.models.service.interfaces.IBusinessService;
import com.movies.rent.models.service.interfaces.IMovieService;
import com.movies.rent.models.service.interfaces.IOperationService;
import com.movies.rent.models.service.interfaces.IUserService;
import com.movies.rent.utilities.constants.ApiRestConstants;
import com.movies.rent.utilities.constants.Messages;
import com.movies.rent.utilities.helpers.HelperMethods;

/**
 * Controller BusinessRestController
 * @date 02/11/2020
 * @author Walter Garcia
 */
@CrossOrigin(origins = { ApiRestConstants.ANGULAR_CORS })
@RestController
@RequestMapping(ApiRestConstants.ENDPOINT_API_BUSINESS)
public class BusinessRestController {

	private final Logger logger = LoggerFactory.getLogger(BusinessRestController.class);
	private String logMsg;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired 
	private IUserService userService;
	
	@Autowired 
	private IMovieService movieService;
	
	@Autowired
	private IOperationService operationService;
	
	
	/**
	 * Controller to save a new business object in database: SALE OR RENT
	 * @param business object to save
	 * @return business object saved
	 * @throws ParseException 
	 */
	@PostMapping(ApiRestConstants.SAVE_BUSINESS)
	@PreAuthorize(ApiRestConstants.IS_ROLE_USER_OR_ROLE_ADMIN)
	public ResponseEntity<?> save(@Valid @RequestBody BusinessDto businessDto) throws ParseException {
		Map<String, Object> response = new HashMap<>();
		Business business;
		String typeOperation = null;
		String strToDate = null;		
		Long idOperation = businessDto.getIdOperation();
	
		//Logging operation
		if(idOperation.intValue() == 1)
			logger.info(Messages.LOG_RENT_A_MOVIE);
		else if(idOperation.intValue() == 2)
			logger.info(Messages.LOG_SALE_MOVIES);
		
		//Check if user exists in database
		Long idUser = businessDto.getIdUser();
		User user = userService.findById(idUser);
		if(user == null) {
			logMsg = String.format(Messages.USER_NOT_FOUND_BY_ID, Long.toString(idUser));
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Check if movie exists in database
		Long idMovie = businessDto.getIdMovie();
		Movie movie = movieService.findById(idMovie);
		if(movie == null) {
			logMsg = String.format(Messages.MOVIE_NOT_FOUND_BY_ID, Long.toString(idMovie));
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Check if operation exists in database
		Operation operation = operationService.findById(idOperation);
		if(operation == null) {
			logMsg = String.format(Messages.OPER_NOT_FOUND_BY_ID, Long.toString(idOperation));
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Validate toDate when is RENT . idOperation 1 = RENT, idOperation 2 = SALE
		strToDate = businessDto.getToDate();
		if(idOperation.intValue() == 1 && strToDate == null) {
			logMsg = Messages.TO_DATE_FIELD_REQUIRED;
			logger.error(logMsg);
			response.put(Messages.ERROR, logMsg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Date sinceDate = new Date();
			Date toDate = (idOperation.intValue() == 1) ? HelperMethods.parseDate(strToDate) : null;
			
			business = new Business();
			business.setQuantity(businessDto.getQuantity());
			business.setSinceDate(sinceDate);
			business.setToDate(toDate);
			business.setPrice(businessDto.getTotal());
			business.setOperation(operation);
			business.setMovie(movie);
			business.setUser(user);
			businessService.save(business);
			
			movie.setAvailability(false);
			movieService.save(movie);
			
			if(idOperation.intValue() == 1) {
				typeOperation = Messages.RENT_MOVIE_SAVED_SUCCESS;
				logger.info(String.format(Messages.RENT_MOVIE, user.getUsername(), businessDto.getQuantity(), 
						businessDto.getTotal(), HelperMethods.dateToString(sinceDate), strToDate, 
						movie.getTitle()));
			}else if(idOperation.intValue() == 2) {
				typeOperation = Messages.SALE_MOVIE_SAVED_SUCCESS;
				logger.info(String.format(Messages.SALE_MOVIE, user.getUsername(), businessDto.getQuantity(), 
						businessDto.getTotal(), HelperMethods.dateToString(sinceDate), movie.getTitle()));
			}
		}catch(DataAccessException e) {
			logMsg = Messages.ERROR_SAVING_BUSINESS;
			logger.error(logMsg, e);
			response.put(Messages.MESSAGE, logMsg);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Messages.MESSAGE, typeOperation);
		response.put(Messages.BUSINESS, business);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
