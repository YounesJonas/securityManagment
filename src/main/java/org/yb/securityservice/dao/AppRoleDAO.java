package org.yb.securityservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yb.securityservice.entities.AppRole;

@RepositoryRestResource
public interface AppRoleDAO extends JpaRepository<AppRole, Long> {

	public AppRole findByRole(String role);

}
