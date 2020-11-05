package com.movies.rent.models.dto;

public class RentedMovies {
	
	private String movie;
	private String sinceDate;
	private String toDate;
	private long remainingDays;
	
	public RentedMovies(String movie, String sinceDate, String toDate, long remainingDays) {
		super();
		this.movie = movie;
		this.sinceDate = sinceDate;
		this.toDate = toDate;
		this.remainingDays = remainingDays;
	}
	
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getSinceDate() {
		return sinceDate;
	}
	public void setSinceDate(String sinceDate) {
		this.sinceDate = sinceDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public long getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(long remainingDays) {
		this.remainingDays = remainingDays;
	}
}
