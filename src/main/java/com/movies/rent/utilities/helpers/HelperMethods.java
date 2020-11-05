package com.movies.rent.utilities.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.movies.rent.models.entity.Movie;
import com.movies.rent.utilities.constants.ApiRestConstants;

public class HelperMethods {

	/**
	 * Method to update information of current movie object retrieved from database with the new information
	 * @param currentMovie :  movie  object retrieved from database
	 * @param updateMovie : movie object sent in the request
	 * @return : new movie object with information sent by put
	 */
	public static Movie updateMovieObj(Movie currentMovie, Movie updateMovie) {
		currentMovie.setTitle(updateMovie.getTitle());
		currentMovie.setDescription(updateMovie.getDescription());
		currentMovie.setRentalPrice(updateMovie.getRentalPrice());
		currentMovie.setSalePrice(updateMovie.getSalePrice());
		
		return updateMovie;
	}
	
	/**
	 * Parse a string to Date
	 * @param strDate to parse
	 * @return Date parsed
	 * @throws ParseException
	 */
	public static Date parseDate(String strDate) throws ParseException{
		Date date = null;
		try {
			date = new SimpleDateFormat(ApiRestConstants.STR_FORMAT_DATE).parse(strDate);
		} catch (ParseException e) {
			throw e;
		}
		
		return date;
	}
	
	/**
	 * Convert date to string
	 * @param date to convert string
	 * @return string of the date
	 */
	public static String dateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(ApiRestConstants.STR_FORMAT_DATE);  
		return dateFormat.format(date);
	}
	
	/**
	 * Calculate days between two dates
	 * @param start date
	 * @param end date
	 * @return days
	 */
	public static long calculateDays(Date start, Date end) {
		long diffInMillies = Math.abs(end.getTime() - start.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}
}
