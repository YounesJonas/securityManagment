package org.yb.securityservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yb.securityservice.entities.AppUser;
import org.yb.securityservice.entities.Status;
import org.yb.securityservice.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class SecurityServiceRest {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public AppUser register(@RequestBody Registartion registrationObject) {
		if (registrationObject != null) {
			return accountService.saveUser(registrationObject.getUsername(), registrationObject.getPassword(),
					registrationObject.getPasswordConfirmation());
		} else {
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getConnectedUser")
	public @ResponseBody ConnectedUser getConnectedUser() {
		ConnectedUser connectedUser = new ConnectedUser();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  (String) auth.getPrincipal();
		
		if(username.isEmpty()) {
			connectedUser.setUsername("");
		}else {
			connectedUser.setUsername(username);
		}
		
		return connectedUser;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public @ResponseBody Iterable<Status> getStatus() {
		return accountService.getAllStatus();
	}
}
