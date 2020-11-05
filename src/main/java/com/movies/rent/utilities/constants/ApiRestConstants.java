package com.movies.rent.utilities.constants;

public class ApiRestConstants {
	/* Hibernate*/
	public static final String HIBERNATE_INITIALIZER		= "hibernateLazyInitializer";
	public static final String HANDLER						= "handler";
	
	/* Entities */
	public static final String OPERATIONS_TABLE				= "operations";
	public static final String ROLES_TABLE					= "roles";
	public static final String MOVIES_TABLE					= "movies";
	public static final String IMAGES_TABLE					= "images";
	public static final String STOCK_TABLE					= "stock";
	public static final String USERS_TABLE					= "users";
	public static final String LIKES_TABLE					= "likes";
	public static final String USER_ROLES					= "user_roles";
	
	/* Entity Attributes */
	public static final String RENTAL_PRICE					= "rental_price";
	public static final String SALE_PRICE					= "sale_price";
	public static final String LAST_NAME					= "last_name";
	public static final String CREATED_AT					= "created_at";
	public static final String SINCE_DATE					= "since_date";
	public static final String TO_DATE						= "to_date";
	public static final String SUB_TOTAL					= "sub_total";
	
	/* Upload Image Attributes */
	public static final String REQUEST_PARAM_IMAGE 			= "imageFile";
	public static final String REQUEST_PARAM_ID				= "id";
	public static final String IMAGE_UPLOADS				= "imageUploads";
	public static final String DIR_IMAGES					= "src/main/resources/static/images";
	public static final String NO_MOVIE_IMAGE				= "no_movie.png";
	public static final String ATTACHMENT_FILE				= "attachment; filename=\" %s \"";
	
	/* Relationship objects*/
	public static final String MOVIE_RELATIONSHIP			= "movie";
	public static final String IMAGES_RELATIONSHIP			= "images";
	
	/* Relationship columns */
	public static final String FK_MOVIE_ID					= "movie_id";
	public static final String FK_OPERATION_ID				= "operation_id";
	public static final String FK_ROLE_ID					= "role_id";
	public static final String FK_USER_ID					= "user_id";
	
	/* ROLES */
	public static final String ROLE 						= "ROLE_";
	public static final String ROLE_ADMIN					= "ADMIN";
	public static final String ROLE_USER					= "USER";
	public static final String IS_ROLE_ADMIN				= "hasRole('ADMIN')";
	public static final String IS_ROLE_USER					= "hasRole('USER')";
	public static final String IS_ROLE_USER_OR_ROLE_ADMIN	= "hasRole('ADMIN') or hasRole('USER')";
	
	/* URL API MOVIES */
	public static final String ANGULAR_CORS					= "http://localhost:4200";
	public static final String ENDPOINT_API_MOVIES			= "/api/movies";
	public static final String GET_ALL_MOVIES				= "/getAll";
	public static final String GET_ALL_MOVIES_PAGE			= "/getAll/page/{page}";
	public static final String GET_MOVIE_BY_ID				= "/getById/{id}";
	public static final String GET_MOVIE_BY_TITLE			= "/getByTitle/{title}";
	public static final String GET_AVAILABILITY_MOVIES		= "/getAvailability";
	public static final String GET_UNAVAILABILITY_MOVIES	= "/getUnavailability";
	public static final String SAVE_MOVIE					= "/save";
	public static final String UPLOAD_MOVIE_IMAGE			= "/upload/image";
	public static final String SHOW_MOVIE_IMAGE				= "/image/show/{imageName:.+}";
	public static final String UPDATE_MOVIE					= "/update/{id}";
	public static final String DELETE_MOVIE					= "/delete/{id}";
	public static final String PUBLIC_ENDPOINT_API_MOVIES	= "/api/movies/**";
	public static final String GET_RENTED_MOVIES			= "/getRentedMoviesByUser/{id}";
	public static final String SAVE_LIKE					= "/like/save";
	public static final String GET_LIKES_BY_MOVIE			= "/like/byIdMovie/id";
	public static final String GET_LIKES_BY_USER			= "/like/byIdUser/id";
	
	/* URL API USER */
	public static final String ENDPOINT_API_USER			= "/api/auth/**";
	public static final String SIGN_IN						= "/signIn";
	public static final String SIGN_UP						= "/signUp";
	
	/* URL API BUSINESS */
	public static final String ENDPOINT_API_BUSINESS		= "/api/business";
	public static final String SAVE_BUSINESS				= "/save";
	
	/* Authorization */
	public static final String AUTHORIZATION				= "Authorization";
	public static final String BEARER_						= "Bearer ";
	public static final String BEARER						= "Bearer";
	public static final String JWT_SECRET					= "${com.movies.rent.app.jwtSecret}";
	public static final String JWT_EXPIRATION				= "${com.movies.rent.app.jwtExpiration}";
	
	/* Format Date */
	public static final String STR_FORMAT_DATE				= "dd-MM-yyyy";
}
