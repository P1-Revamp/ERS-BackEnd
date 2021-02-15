package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aspect.LoggingAspect;
import com.revature.model.Users;
import com.revature.model.DTO.LoginDTO;
import com.revature.service.LoginService;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	
	private static Logger log = LogManager.getLogger(LoginController.class);

	LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping
	public ResponseEntity<Users> getUserByUsernameAndPassword(@RequestBody LoginDTO loginInfo) {
		
		log.info("in controller, info");
		log.debug("in controller, debug");
		
		String username = loginInfo.getUsername();
		String password = loginInfo.getPassword();
		
		Users user = loginService.getUserByUsernameAndPassword(username, password);
		
		if (user == null) return ResponseEntity.status(404).build();
		return ResponseEntity.status(200).body(user);
		
	}
//	
//	@PostMapping("/create")
//	public ResponseEntity createAccount(@RequestBody Users user) {
//		
//		if (loginService.createUser(user)) {
//			return ResponseEntity.status(201).build();
//		}
//		return ResponseEntity.status(500).build();
//		
//	}

}
