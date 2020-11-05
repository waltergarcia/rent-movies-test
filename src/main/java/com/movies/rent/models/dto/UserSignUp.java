package com.movies.rent.models.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSignUp {
	
	@NotBlank
    @Size(max = 50)
    private String name;
	
	@NotBlank
    @Size(max = 50)
    private String lastName;
	
	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
	
	@NotBlank
    @Size(min = 3, max = 50)
    private String username;
	
	@NotBlank
    @Size(min = 6, max = 75)
    private String password;
	
	@NotBlank
    @Size(max = 100)
    private String address;
 
	@NotBlank
    @Size(max = 9)
    private String phone;
    
    private Set<String> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
