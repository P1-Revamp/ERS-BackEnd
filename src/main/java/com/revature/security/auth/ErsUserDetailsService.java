package com.revature.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ErsUserDetailsService implements UserDetailsService {
	
	private final ErsUserRepository ersUserRepository;
	
	//qualifier in case there is more than 1 implementation
	@Autowired
	public ErsUserDetailsService(@Qualifier("fake") ErsUserRepository ersUserRepository) {
		super();
		this.ersUserRepository = ersUserRepository;
	}

	@Override
	public ErsUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return ersUserRepository
				.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
	}

}
