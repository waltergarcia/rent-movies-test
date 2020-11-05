package com.movies.rent.models.dto;

import javax.validation.constraints.NotBlank;

public class LikeDto {

	@NotBlank
    private Long idUser;
	
	@NotBlank
    private Long idMovie;

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
}
