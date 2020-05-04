package org.yb.securityservice.service;

import org.springframework.stereotype.Component;
import org.yb.securityservice.entities.AppRole;
import org.yb.securityservice.entities.AppUser;
import org.yb.securityservice.entities.Status;

@Component
public interface AccountService {

	public AppUser saveUser(AppUser user);

	public AppUser saveUser(String username, String password, String passwordConfirmation);

	public AppRole saveRole(AppRole role);

	public Status findByStaus(String status);

	public AppUser findByUserName(String username);

	public AppRole findByRole(String role);

	public void addRoleToUser(String role, String user);

	public void deleteUsers();

	public void deleteRole();

	public Iterable<Status> getAllStatus();

	public Status saveStatus(Status staus);
	
	public void deleteStatus();
}
