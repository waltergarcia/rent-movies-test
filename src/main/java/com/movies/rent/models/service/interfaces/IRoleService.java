package com.movies.rent.models.service.interfaces;

import com.movies.rent.models.entity.Role;

public interface IRoleService {
	Role findByName(String name);
}
