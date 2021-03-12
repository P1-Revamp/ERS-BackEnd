package com.revature.security.auth;

import java.util.Optional;

public interface ErsUserSecurityService {
	
	 Optional<ErsUserDetails> selectApplicationUserByUsername(String username);

}
