package com.movies.rent.auth;

import io.jsonwebtoken.*;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.movies.rent.utilities.constants.ApiRestConstants;
import com.movies.rent.utilities.constants.Messages;

@Component
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value(ApiRestConstants.JWT_SECRET)
	private String jwtSecret;

	@Value(ApiRestConstants.JWT_EXPIRATION)
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {

		User userPrincipal = (User) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error(Messages.INVALID_JWT_SIGNATURE, e);
		} catch (MalformedJwtException e) {
			logger.error(Messages.INVALID_JWT_TOKEN, e);
		} catch (ExpiredJwtException e) {
			logger.error(Messages.EXPIRED_JWT_TOKEN, e);
		} catch (UnsupportedJwtException e) {
			logger.error(Messages.UNSUPORTED_JWT_TOKEN, e);
		} catch (IllegalArgumentException e) {
			logger.error(Messages.JWT_CLAIMS_EMPTY, e);
		}

		return false;
	}
}
