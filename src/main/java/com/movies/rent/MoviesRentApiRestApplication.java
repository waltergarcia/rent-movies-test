package com.movies.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesRentApiRestApplication {

	/*@Autowired(required=true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/
	
	public static void main(String[] args) {
		SpringApplication.run(MoviesRentApiRestApplication.class, args);
	}
}
