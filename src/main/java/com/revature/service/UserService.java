package com.revature.service;

import java.util.Optional;

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
	public Users getUserByUsernameAndPassword(String username, String password) {
	
		
		Optional<Users> userOpt = userRepository.findByUsername(username);
//		Optional<Users> userOptt = userRepository.findById(1);
//		List<Users> userAll = userRepository.findAll();
//		System.out.println("userAll: " + userAll);
//		Users usPass = userRepository.findByPassword(password);
//		System.out.println("usPass: " + usPass);
		
		Users user = null;
//		System.out.println("userOpt.isPresent(): " + userOpt.isPresent());
//		System.out.println("userOptt.isPresent(): " + userOptt.isPresent());
		if (userOpt.isPresent()) user = userOpt.get();
		
//		System.out.println("user: " + user);
		
//		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		
		String hashedPassword = String.valueOf(password.hashCode());
		
//		String hashedPassword = Encrypt.encryptPassword(loginInfo.getPassword());
		
//		final EncryptionUtility encryptionUtility = new EncryptionUtility();
//		String decryptPass = EncryptionUtility.decrypt(loginInfo.getPassword(), encryptionUtility.getKey());
//		loginInfo.setPassword(decryptPass);
		
//		EncryptionUtility eu = null;
//		try {
//			eu = new EncryptionUtility();
//			
//		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//			e.printStackTrace();
//		}
//		String encryptPass = null;
//		try {
//			encryptPass = EncryptionUtility.encrypt(loginInfo.getPassword(), eu.getKey());
//		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
//	
//			e.printStackTrace();
//		}
		
//		System.out.println("password: " + password);
//		System.out.println("hashedPassword: " + hashedPassword);
		

		
		if (user != null && user.getPassword().equals(password)) return user;
		return null;
	}

	@Transactional
	public Users getUserById(Integer id) {
		
		Optional<Users> userOpt = userRepository.findById(id);
		
		Users user = null;
		if (userOpt.isPresent()) user = userOpt.get();
		
		return user;
	}

	@Transactional
	public boolean getUserByUsername(String username) {
		
		Optional<Users> userOpt = userRepository.findByUsername(username);
		
		if (userOpt.isPresent()) return true;
		return false;
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

}
