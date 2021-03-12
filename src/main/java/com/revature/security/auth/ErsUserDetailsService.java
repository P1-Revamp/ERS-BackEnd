package com.revature.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ErsUserDetailsService implements UserDetailsService {
	
	private final ErsUserSecurityService ersUserSecurityService;
	
	@Autowired
	public ErsUserDetailsService(ErsUserSecurityService ersUserSecurityService) {
		super();
		this.ersUserSecurityService = ersUserSecurityService;
	}

	@Override
	public ErsUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return ersUserSecurityService
				.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
	}

}
