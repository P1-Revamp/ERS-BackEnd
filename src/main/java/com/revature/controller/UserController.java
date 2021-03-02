package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Users;
import com.revature.service.LoginService;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {

	LoginService loginService;
	
	@Autowired
	public UserController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer[]> login(@RequestBody Users userCredentials) {
		
		Users user = loginService.getUserByUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
		
		if (user == null) return ResponseEntity.status(404).build();
		return ResponseEntity.status(200).body(new Integer[] {user.getUserId(), user.getRole().getRoleId()});
		
	}
	
	@PostMapping("/username")
	public ResponseEntity<Boolean> checkIfUsernameAlreadyExists(@RequestBody Users user) {
		
		if (loginService.getUserByUsername(user.getUsername())) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.OK).body(false);
		
	}
	
	@PatchMapping("/username")
	public ResponseEntity<Boolean> UpdateUsername(@RequestBody Users userUpdate) {
		
		if (loginService.updateUsername(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PatchMapping("/email")
	public ResponseEntity<Boolean> UpdateEmail(@RequestBody Users userUpdate) {
		
		if (loginService.updateEmail(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PatchMapping("/password")
	public ResponseEntity<Boolean> UpdatePassword(@RequestBody Users userUpdate) {
		
		if (loginService.updatePassword(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
		
		Users user = loginService.getUserById(id);
		
		if (user == null) return ResponseEntity.status(404).build();
		return ResponseEntity.status(200).body(user);
		
	}

}
