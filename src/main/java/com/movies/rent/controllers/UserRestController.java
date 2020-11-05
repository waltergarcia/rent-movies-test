package com.movies.rent.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.rent.auth.JwtProvider;
import com.movies.rent.models.dto.JwtResponse;
import com.movies.rent.models.dto.UserSignIn;
import com.movies.rent.models.dto.UserSignUp;
import com.movies.rent.models.entity.Role;
import com.movies.rent.models.entity.User;
import com.movies.rent.models.service.interfaces.IUserService;
import com.movies.rent.models.service.interfaces.IRoleService;
import com.movies.rent.utilities.constants.ApiRestConstants;
import com.movies.rent.utilities.constants.Messages;

/**
 * Controller UserRestController
 * @date 01/11/2020
 * @author Walter Garcia
 */
@CrossOrigin(origins = { ApiRestConstants.ANGULAR_CORS })
@RestController
@RequestMapping(ApiRestConstants.ENDPOINT_API_USER)
public class UserRestController {
	
	private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    IUserService userService;
 
    @Autowired
    IRoleService roleService;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
    
    /**
     * Login to get a token
     * @param userLogin credentials
     * @return a new token
     */
    @PostMapping(ApiRestConstants.SIGN_IN)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserSignIn userLogin) {
 
    	logger.info(Messages.LOG_SIGN_IN_USER);
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userLogin.getUsername(),
                		userLogin.getPassword()
                )
        );
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    /**
     * Register a new user in database
     * @param userSignUp user to register
     * @return the user registered
     */
    @PostMapping(ApiRestConstants.SIGN_UP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUp userSignUp) {
    	Map<String, Object> response = new HashMap<>();
    	User saveUser;
    	String error;
    	
    	logger.info(Messages.LOG_SIGN_UP_USER);
    	
    	//Check if username exists in database
    	String username = userSignUp.getUsername();    	
    	if(userService.existsByUsername(username)) {
    		error = String.format(Messages.USERNAME_ALREADY_EXISTS, username);
    		logger.error(error);
    		response.put(Messages.ERROR, error);
    		response.put(Messages.ERROR, String.format(Messages.USERNAME_ALREADY_EXISTS, username));
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    	}
    	
    	//Check if email exists in database
    	String email = userSignUp.getEmail();
    	if(userService.existsByEmail(email)) {
    		error = String.format(Messages.EMAIL_ALREADY_EXISTS, email);
    		logger.error(error);
    		response.put(Messages.ERROR, error);
    		response.put(Messages.ERROR, String.format(Messages.EMAIL_ALREADY_EXISTS, email));
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    	
    	//Set entity user with request data
    	User user = new User(userSignUp.getName(), userSignUp.getLastName(), userSignUp.getEmail(), userSignUp.getUsername(),
    						 userSignUp.getPassword(), userSignUp.getAddress(), userSignUp.getPhone());
    	
    	//Get roles from the request
    	Set<String> strRoles = userSignUp.getRoles();
        Set<Role> roles = new HashSet<>();
    	
        //Find roles in database to set the new user
    	strRoles.forEach(role -> {
    		switch(role.toUpperCase()) {
    			case ApiRestConstants.ROLE_ADMIN:
    				String roleAdmin = ApiRestConstants.ROLE.concat(ApiRestConstants.ROLE_ADMIN);
    				Role adminRole = roleService.findByName(roleAdmin);
    				logger.info(String.format(Messages.LOG_SET_ROLE, roleAdmin));
    				roles.add(adminRole);
    			break;
    			
    			default:
    	              Role userRole = roleService.findByName(ApiRestConstants.ROLE.concat(ApiRestConstants.ROLE_USER));
    	              logger.info(String.format(Messages.LOG_SET_ROLE_NOT_FOUND, role));
    	              roles.add(userRole);  
    		}
    	});
    	
    	//Set roles the new user and save it
    	try {
    		user.setRoles(roles);
	    	user.setPassword(encoder.encode(user.getPassword()));
	    	saveUser = userService.save(user);
    	}catch(DataAccessException e) {
    		error = Messages.ERROR_SAVING_USER;
    		logger.error(error, e);
    		response.put(Messages.ERROR, error);
			response.put(Messages.MESSAGE, Messages.ERROR_SAVING_USER);
			response.put(Messages.ERROR, e.getMessage().concat(Messages.POINTS).concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	response.put(Messages.MESSAGE, Messages.USER_SAVED_SUCCESSFUL);
		response.put(Messages.USER, saveUser);
 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
