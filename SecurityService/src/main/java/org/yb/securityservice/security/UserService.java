package org.yb.securityservice.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yb.securityservice.entities.AppUser;
import org.yb.securityservice.service.AccountService;
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser appUser = accountService.findByUserName(username);
		if(appUser==null) throw new UsernameNotFoundException("invalid User! ");
		
		// collection of garantedAuthority
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		appUser.getRoles().forEach(
				(r) -> authorities.add(new SimpleGrantedAuthority(r.getRole()))
				);
		
		
		return new User(appUser.getUserName(), appUser.getPassword(), authorities);
	}

}
