package org.yb.securityservice.dao;

import org.yb.securityservice.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatusDAO extends JpaRepository<Status, Integer> {

	Status findByLibelle(String libelle);
	
}
