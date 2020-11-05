package com.movies.rent.models.dto;

import com.movies.rent.utilities.constants.ApiRestConstants;

public class JwtResponse {
	private String token;
	private String type = ApiRestConstants.BEARER;

	public JwtResponse(String accessToken) {
		this.token = accessToken;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}
}
