package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Users;


public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByUsername(String username);
	Users findByPassword(String password);

}
