package com.movies.rent.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movies.rent.utilities.constants.ApiRestConstants;

@Entity
@JsonIgnoreProperties({ ApiRestConstants.HIBERNATE_INITIALIZER, ApiRestConstants.HANDLER })
public class Business implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private int quantity;

	@Column(name = ApiRestConstants.SINCE_DATE)
	@Temporal(TemporalType.DATE)
	private Date sinceDate;

	@Column(name = ApiRestConstants.TO_DATE)
	@Temporal(TemporalType.DATE)
	private Date toDate;

	@Column(name = ApiRestConstants.SUB_TOTAL, nullable = false)
	private double price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ApiRestConstants.FK_USER_ID)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ApiRestConstants.FK_MOVIE_ID)
	private Movie movie;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ApiRestConstants.FK_OPERATION_ID)
	private Operation operation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getSinceDate() {
		return sinceDate;
	}

	public void setSinceDate(Date sinceDate) {
		this.sinceDate = sinceDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public double getSubTotal() {
		return this.quantity * this.price;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", quantity=" + quantity + ", sinceDate=" + sinceDate + ", toDate=" + toDate
				+ ", price=" + price + ", user=" + user + ", movie=" + movie + ", operation=" + operation + "]";
	}
}
