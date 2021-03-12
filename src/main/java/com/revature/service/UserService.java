package com.revature.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.Base64.Decoder;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Users;
import com.revature.repository.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Transactional
	public Users getUserById(Integer id) {
		
		Optional<Users> userOpt = userRepository.findById(id);
		
		Users user = null;
		if (userOpt.isPresent()) user = userOpt.get();
		
		return user;
	}

	@Transactional
	public Optional<Users> getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Transactional
	public boolean updateUsername(Users userUpdate) {
		
		try {
		Optional<Users> userOpt = userRepository.findById(userUpdate.getUserId());
		
		Users user = null;		
		if (userOpt.isPresent()) user = userOpt.get();
		else return false;
		
		user.setUsername(userUpdate.getUsername());
		userRepository.save(user);
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean updateEmail(Users userUpdate) {
		
		try {
		Optional<Users> userOpt = userRepository.findById(userUpdate.getUserId());
		
		Users user = null;		
		if (userOpt.isPresent()) user = userOpt.get();
		else return false;
		
		user.setEmail(userUpdate.getEmail());
		userRepository.save(user);
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean updatePassword(Users userUpdate) {
		
		try {
		Optional<Users> userOpt = userRepository.findById(userUpdate.getUserId());
		
		Users user = null;		
		if (userOpt.isPresent()) user = userOpt.get();
		else return false;
		
		user.setPassword(userUpdate.getPassword());
		userRepository.save(user);
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getUsernameFromHeader(String header) {
		String usernameAndPassword = header.replace("Basic ", "");
		Decoder decoder = Base64.getDecoder();
		String usernameAndPasswordDecoded = new String(decoder.decode(usernameAndPassword), StandardCharsets.UTF_8);
		
		String[] usernameAndPasswordArray = usernameAndPasswordDecoded.split(":");
		return usernameAndPasswordArray[0];
	}
	
//	public String getPasswordFromHeader(String header) {
//		String usernameAndPassword = header.replace("Basic ", "");
//		Decoder decoder = Base64.getDecoder();
//		String usernameAndPasswordDecoded = new String(decoder.decode(usernameAndPassword), StandardCharsets.UTF_8);
//		
//		String[] usernameAndPasswordArray = usernameAndPasswordDecoded.split(":");
//		return usernameAndPasswordArray[1];
//	}

}
