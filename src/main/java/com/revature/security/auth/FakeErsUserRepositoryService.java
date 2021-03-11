package com.revature.security.auth;

import static com.revature.security.ErsUserRoles.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.revature.model.Users;
import com.revature.repository.UserRepository;

//must be altered to allign with a real DB
@Repository("fake")
public class FakeErsUserRepositoryService implements ErsUserRepository {

	private final PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
	@Autowired
	public FakeErsUserRepositoryService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
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
		
		
//		return getApplicationUsers().stream()
//					.filter(applicationUser -> username.equals(applicationUser.getUsername()))
//					.findFirst();
	}
	
	//probably grabs from real repo?
	private List<ErsUserDetails> getApplicationUsers() {
		List<ErsUserDetails> applicationUsers = Lists.newArrayList(
			new ErsUserDetails(
				EMPLOYEE.getGrantedAuthorities(),
				passwordEncoder.encode("password"),
				"annasmith",
				1,
				1,
				true,
				true,
				true,
				true
			)
		);
		
		return applicationUsers;
		
	}

}
