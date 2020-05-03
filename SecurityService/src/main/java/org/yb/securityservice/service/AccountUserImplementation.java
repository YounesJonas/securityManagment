package org.yb.securityservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yb.securityservice.dao.AppRoleDAO;
import org.yb.securityservice.dao.AppUserDAO;
import org.yb.securityservice.dao.StatusDAO;
import org.yb.securityservice.entities.AppRole;
import org.yb.securityservice.entities.AppUser;
import org.yb.securityservice.entities.Status;

@Service
@Transactional
public class AccountUserImplementation implements AccountService {

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private AppRoleDAO appRoleDAO;

	@Autowired
	private StatusDAO statusDAO;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public AppUser saveUser(AppUser user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return appUserDAO.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {

		return appRoleDAO.save(role);
	}

	@Override
	public AppUser findByUserName(String username) {

		return appUserDAO.findByUserName(username);
	}

	@Override
	public AppRole findByRole(String role) {

		return appRoleDAO.findByRole(role);
	}

	@Override
	public void addRoleToUser(String role, String user) {

		AppUser appuser = appUserDAO.findByUserName(user);
		AppRole approle = appRoleDAO.findByRole(role);

		List<AppRole> listeRole = new ArrayList<>();
		listeRole = appuser.getRoles();
		listeRole.add(approle);

		appuser.setRoles(listeRole);

		appUserDAO.save(appuser);

	}

	@Override
	public AppUser saveUser(String username, String password, String passwordConfirmation) throws RuntimeException {

		AppUser appuser = new AppUser();
		// check if user exist in db
		if (appUserDAO.findByUserName(username) != null) {
			throw new RuntimeException("this user exist in data base, try with an other username!");
		} else {
			if (password != null) {
				if (!password.equals(passwordConfirmation)) {
					throw new RuntimeException("please confirm your password!");
				} else {

					appuser.setUserName(username);
					appuser.setPassword(bcrypt.encode(password));
					appuser.setActived(true);
					ArrayList<AppRole> roles = new ArrayList<>();
					roles.add(appRoleDAO.findByRole("USER"));
					appuser.setRoles(roles);
					appUserDAO.save(appuser);
				}
			} else {
				new RuntimeException("an error was occured");
			}
		}
		return appuser;
	}

	@Override
	public void deleteUsers() {

		appUserDAO.deleteAll();
	}

	@Override
	public void deleteRole() {

		appRoleDAO.deleteAll();
	}

	@Override
	public Status findByStaus(String status) {

		return statusDAO.findByLibelle(status);
	}

	@Override
	public Iterable<Status> getAllStatus() {

		return statusDAO.findAll();
	}

	@Override
	public Status saveStatus(Status status) {

		return statusDAO.save(status);
	}

	@Override
	public void deleteStatus() {
		statusDAO.deleteAll();

	}

}
