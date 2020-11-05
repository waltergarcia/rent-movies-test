package com.movies.rent.utilities.constants;

public class CustomQueries {
	public static final String SELECT_MOVIE_BY_TITLE			= "SELECT m FROM Movie m WHERE LOWER(m.title) LIKE %?1%";
}
