package com.revature.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Users;
import com.revature.security.auth.ErsUserDetails;
import com.revature.security.auth.ErsUserDetailsService;
import com.revature.service.UserService;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {

	private UserService userService;
	private ErsUserDetailsService ersUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController(UserService userService, ErsUserDetailsService ersUserDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.ersUserDetailsService = ersUserDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE, FINANCIAL_MANAGER')")
	@PostMapping("/login")
	public ResponseEntity<Integer[]> login(@RequestHeader("Authorization") String userInfo) {
		
		String username = userService.getUsernameFromHeader(userInfo);
		ErsUserDetails userDetails = null;
		
		try {
			userDetails = ersUserDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new Integer[] {userDetails.getUserId(), userDetails.getRoleId()});				
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE, FINANCIAL_MANAGER')")
	@PostMapping("/username")
	public ResponseEntity<Boolean> checkIfUsernameAlreadyExists(@RequestBody Users user) {
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(user.getUsername()).isPresent());
	}
	
	@PreAuthorize("hasAuthority('employee:write')")
	@PatchMapping("/username")
	public ResponseEntity<Boolean> updateUsername(@RequestBody Users userUpdate) {
		
		if (userService.updateUsername(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PreAuthorize("hasAuthority('employee:write')")
	@PatchMapping("/email")
	public ResponseEntity<Boolean> updateEmail(@RequestBody Users userUpdate) {
		
		if (userService.updateEmail(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PreAuthorize("hasAuthority('employee:write')")
	@PatchMapping("/password")
	public ResponseEntity<Boolean> updatePassword(@RequestBody Users userUpdate) {
		
		userUpdate.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
		
		if (userService.updatePassword(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PreAuthorize("hasAuthority('employee:read')")
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@RequestHeader("Authorization") String userInfo, @PathVariable("id") Integer id) {
		
		Optional<Users> userOpt = userService.getUserByUsername(userService.getUsernameFromHeader(userInfo));
		if (!userOpt.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		Users user = userOpt.get();
		
		System.out.println("id: " + id);
		System.out.println("getUserId: " + user.getUserId());
		
		if (user.getUserId() != id) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.status(HttpStatus.OK).body(user);
		
	}

}
