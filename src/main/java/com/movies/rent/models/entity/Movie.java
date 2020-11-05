package com.movies.rent.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movies.rent.utilities.constants.ApiRestConstants;

@Entity
@Table(name = ApiRestConstants.MOVIES_TABLE)
@JsonIgnoreProperties({ ApiRestConstants.HIBERNATE_INITIALIZER, ApiRestConstants.HANDLER })
public class Movie implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;
	
	private String description;
	
	@Column(nullable = false, name = ApiRestConstants.RENTAL_PRICE)
	private double rentalPrice;

	@Column(nullable = false, name = ApiRestConstants.SALE_PRICE)
	private double salePrice;
	
	private boolean availability;
	
	private String image;
	
	private Integer likes;
    
	public Movie() {
		this.availability = true;
		this.likes = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalProce) {
		this.rentalPrice = rentalProce;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + ", rentalPrice=" + rentalPrice
				+ ", salePrice=" + salePrice + ", availability=" + availability + ", image=" + image + ", likes="
				+ likes + "]";
	}
}
