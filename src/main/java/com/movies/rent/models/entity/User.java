package com.movies.rent.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movies.rent.utilities.constants.ApiRestConstants;

@Entity
@Table(name = ApiRestConstants.USERS_TABLE)
@JsonIgnoreProperties({ ApiRestConstants.HIBERNATE_INITIALIZER, ApiRestConstants.HANDLER })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String name;

	@NotEmpty
	@Column(name = ApiRestConstants.LAST_NAME, length = 50)
	private String lastName;

	@NotEmpty
	@Email
	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String username;
	
	@NotEmpty
	@Column(nullable = false, length = 75)
	private String password;
	
	@NotEmpty
	@Column(nullable = false, length = 100)
	private String address;

	@Column(length = 9)
	private String phone;
	
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = ApiRestConstants.USER_ROLES, joinColumns = @JoinColumn(name = ApiRestConstants.FK_USER_ID), 
      			inverseJoinColumns = @JoinColumn(name = ApiRestConstants.FK_ROLE_ID))
    private Set<Role> roles = new HashSet<>();

	public User() {}

	public User(String name, String lastName, String email,
			 	String username, String password, String address, String phone) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.enabled = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<Role> getRoles() {
        return roles;
    }
 
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
