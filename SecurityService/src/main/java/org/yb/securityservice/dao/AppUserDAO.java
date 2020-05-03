package org.yb.securityservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yb.securityservice.entities.AppUser;

@RepositoryRestResource
public interface AppUserDAO extends JpaRepository<AppUser, Long> {

	public AppUser findByUserName(String userName);
	
}
