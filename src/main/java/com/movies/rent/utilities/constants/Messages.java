package com.movies.rent.utilities.constants;

public class Messages {
	/* Error handled messages in Controllers*/
	public static final String ERROR 					= "error";
	public static final String MESSAGE 					= "message";
	public static final String MOVIE 					= "movie";
	public static final String LIKE 					= "like";
	public static final String USER 					= "user";
	public static final String BUSINESS					= "business_transaction";
	public static final String RENTED_MOVIES			= "rented_movies";
	public static final String POINTS 					= " : ";
	public static final String ERROR_GETTING_MOVIE		= "Error getting movie.";
	public static final String MOVIE_NOT_FOUND_BY_ID	= "Movie id %s not found!";
	public static final String MOVIE_NOT_FOUND_BY_TITLE	= "Movie title '%s' not found!";
	public static final String MOVIE_UNAVAILABILITY		= "There is not availability movies!";
	public static final String MOVIE_AVAILABILITY		= "Goog, all your movies are availability!";
	public static final String ERROR_SAVING_MOVIE		= "Error saving movie.";
	public static final String MOVIE_SAVED_SUCCESSFUL	= "Movie saved successful.";
	public static final String ERROR_UPDATING_MOVIE_ID	= "Error updating movie id %s. Not found in database!";
	public static final String ERROR_UPDATING_MOVIE		= "Error updating movie.";
	public static final String MOVIE_UPDATED_SUCCESSFUL = "Movie updated successful ";
	public static final String ERROR_DELETING_MOVIE		= "Error deleting movie.";
	public static final String ERROR_DELETING_MOVIE_ID	= "Error deleting movie id %s. Not found in database!";
	public static final String MOVIE_DELETED_SUCCESSFUL = "Movie deleted successful.";
	public static final String ERROR_SAVING_LIKE		= "Error saving like.";
	public static final String LIKE_SAVED_SUCCESSFUL	= "Like saved successful.";
	public static final String ERROR_SAVING_USER		= "Error saving user.";
	public static final String USER_SAVED_SUCCESSFUL	= "User saved successful.";
	public static final String USERNAME_ALREADY_EXISTS 	= "Username '%s' is already taken!";
	public static final String EMAIL_ALREADY_EXISTS 	= "Email %s is already in use!";
	public static final String ERROR_UPLOADING_IMAGE	= "Error uploading image movie.";
	public static final String ERROR_SHOWING_IMAGE		= "Error showing image movie.";
	public static final String IMAGE_UPLOAD_SUCCESFUL	= "Image upload successful %s";
	public static final String USER_NOT_FOUND_BY_ID		= "User id %s not found!";
	public static final String OPER_NOT_FOUND_BY_ID		= "Operation id %s not found!";
	public static final String TO_DATE_FIELD_REQUIRED	= "Field toDate is required for RENT a movie!";
	public static final String ERROR_SAVING_BUSINESS	= "Error saving rent or buy of movie.";
	public static final String RENT_MOVIE_SAVED_SUCCESS = "Rent of movie saved successful.";
	public static final String SALE_MOVIE_SAVED_SUCCESS	= "Sale of movie saved successful.";
	public static final String ERROR_GETTING_BUSINESS	= "Error getting business.";
	
	/* Error messages in Repositories*/
	public static final String USER_NOT_FOUND 			= "User %s not found!";
	
	/* Error messages in Service */
	public static final String ROLE			 			= "Role: %s";
	public static final String ERROR_LOADING_IMAGE		= "Error loading movie image: {}";
	public static final String SAVING_MOVIE_IMAGE		= "Saving movie image at {}";
	public static final String CREATING_DIR_IMAGES		= "Creating folder images {}";
	public static final String DELETING_DIR_IMAGES		= "Deleting folder images {}";
	
	/* Error messages in Auth*/
	public static final String AUTH_MANAGER 			= "authenticationManager";
	public static final String UNAUTHORIZED_ERROR		= "Unauthorized error. Message - {}";
	public static final String UNAUTHORIZED				= "Error -> Unauthorized";
	public static final String CANNOT_SET_USER_AUTH		= "Can NOT set user authentication -> Message: {}";
	public static final String INVALID_JWT_SIGNATURE	= "Invalid JWT signature -> Message: {} ";
	public static final String INVALID_JWT_TOKEN		= "Invalid JWT token -> Message: {}";
	public static final String EXPIRED_JWT_TOKEN		= "Expired JWT token -> Message: {}";
	public static final String UNSUPORTED_JWT_TOKEN 	= "Unsupported JWT token -> Message: {}";
	public static final String JWT_CLAIMS_EMPTY			= "JWT claims string is empty -> Message: {}";
	public static final String ERROR_AUTHENTICATION		= "Error authentication: %s";
	
	/* Logger messages in controllers */
	public static final String LOG_GET_ALL_MOVIES		= "Get all movies : %s items found";
	public static final String LOG_GET_PAGE_MOVIES		= "Get movies from page %s";
	public static final String LOG_PAGE_SIZE_MOVIES		= "%s movies found in page %s";
	public static final String LOG_GET_MOVIE_BY_ID		= "Get movie by id %s";
	public static final String LOG_GET_MOVIE_BY_TITLE	= "Get movie by title '%s'";
	public static final String LOG_AVAILABILITY_MOVIES	= "Get availability movies";
	public static final String LOG_UNAVAILABILITY_MOVIES= "Get unavailability movies";
	public static final String LOG_RENTED_MOVIES_BY_USER= "Get movies rented by user id %s";
	public static final String LOG_SAVE_MOVIE 			= "Save movie";
	public static final String LOG_UPDATE_MOVIE 		= "Update movie";
	public static final String LOG_DELETE_MOVIE 		= "Delete movie";
	public static final String LOG_UPLOAD_IMAGE 		= "Upload movie image";
	public static final String LOG_SHOW_IMAGE 			= "Show movie image";
	public static final String LOG_SIGN_IN_USER	 		= "Sign In user";
	public static final String LOG_SIGN_UP_USER			= "Sign Up user";
	public static final String LOG_SET_ROLE_NOT_FOUND	= "Role '%s' not found in database, set 'ROLE_USER' by default";
	public static final String LOG_SET_ROLE				= "Set role '%s'";
	public static final String LOG_RENT_A_MOVIE			= "Rent movie";
	public static final String LOG_SALE_MOVIES			= "Sale movies";
	public static final String LOG_LIKE_MOVIES			= "Like movie";
	public static final String LOG_LIKED_MOVIES			= "Liked movie '%s', user '%s'";
	
	public static final String RENT_MOVIE				= "Rent movie -> user[%s], quantity[%s], total[$%s], since[%s], to[%s], movie[%s]";
	public static final String SALE_MOVIE				= "Sale movie -> user[%s], quantity[%s], total[$%s], date[%s], movie[%s]";
}
