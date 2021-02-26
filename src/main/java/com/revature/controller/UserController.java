package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<Integer> login(@RequestBody Users userCredentials) {
		
		Users user = loginService.getUserByUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
		
		if (user == null) return ResponseEntity.status(404).build();
		return ResponseEntity.status(200).body(user.getUserId());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
		
		Users user = loginService.getUserById(id);
		
		if (user == null) return ResponseEntity.status(404).build();
		return ResponseEntity.status(200).body(user);
		
	}

}
