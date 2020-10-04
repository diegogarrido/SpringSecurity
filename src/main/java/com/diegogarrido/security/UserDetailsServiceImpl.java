package com.diegogarrido.security;

import com.diegogarrido.model.User;
import com.diegogarrido.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService uService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!uService.usernameExists(username)) {
			throw new UsernameNotFoundException(username);
		}
		User user = uService.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), WebSecurity.getAuthorities(user));
	}
}
