package com.revature.security.auth;

import java.util.Optional;

public interface ErsUserRepository {
	
	 Optional<ErsUserDetails> selectApplicationUserByUsername(String username);

}
