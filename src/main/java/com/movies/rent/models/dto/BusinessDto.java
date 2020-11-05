package com.movies.rent.models.dto;

import javax.validation.constraints.NotBlank;

public class BusinessDto {
	
	@NotBlank
	private Long idUser;
	
	@NotBlank
	private Long idMovie;
	
	@NotBlank
	private Long idOperation;
	
	@NotBlank
	private int quantity;
	
	@NotBlank
	private double total;

	private String toDate;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Long idMovie) {
		this.idMovie = idMovie;
	}

	public Long getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(Long idOperation) {
		this.idOperation = idOperation;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
