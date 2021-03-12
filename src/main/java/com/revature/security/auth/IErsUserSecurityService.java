package com.revature.security.auth;

import static com.revature.security.ErsUserRoles.EMPLOYEE;
import static com.revature.security.ErsUserRoles.FINANCIAL_MANAGER;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Users;
import com.revature.repository.UserRepository;

@Service
public class IErsUserSecurityService implements ErsUserSecurityService {

	private UserRepository userRepository;
	
	@Autowired
	public IErsUserSecurityService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Optional<ErsUserDetails> selectApplicationUserByUsername(String username) {
		
		Optional<Users> userOpt = userRepository.findByUsername(username);
		Users user = null;
		
		if (userOpt.isPresent()) {
			user = userOpt.get();
			
			if (user.getRole().getRoleId() == 1) {
				return Optional.ofNullable(
					new ErsUserDetails(
							FINANCIAL_MANAGER.getGrantedAuthorities(), 
							user.getPassword(), 
							user.getUsername(), 
							user.getUserId(), 
							user.getRole().getRoleId(), 
							true, 
							true, 
							true, 
							true));
			} else {
				return Optional.ofNullable(
						new ErsUserDetails(
								EMPLOYEE.getGrantedAuthorities(), 
								user.getPassword(), 
								user.getUsername(), 
								user.getUserId(), 
								user.getRole().getRoleId(), 
								true, 
								true, 
								true, 
								true));
			}
		}
		
		return Optional.empty();
	}

}
