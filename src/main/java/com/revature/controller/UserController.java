package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController(UserService userService, ErsUserDetailsService ersUserDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.ersUserDetailsService = ersUserDetailsService;
		this.passwordEncoder = passwordEncoder;
//		this.passwordEncoder = passwordEncoder;
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<Integer[]> login(@RequestBody Users userCredentials) {
//		
//		UserPrincipal user = (UserPrincipal) ersUserDetailsService.loadUserByUsername(userCredentials.getUsername());
//		
////		if (user.getPassword().equals(userCredentials.getPassword())) 
//		return ResponseEntity.status(HttpStatus.OK).body(new Integer[] {user.getUserId(), user.getRoleId()});
//		
////		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////		Users user = userService.getUserByUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
////		
////		if (user == null) return ResponseEntity.status(404).build();
////		return ResponseEntity.status(200).body(new Integer[] {user.getUserId(), user.getRole().getRoleId()});
////		
//	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer[]> login(@RequestBody Users user) {
		
		ErsUserDetails userDetails = ersUserDetailsService.loadUserByUsername(user.getUsername());
		
//		System.out.println("DB password: " + userDetails.getPassword());
//		System.out.println("Entered password: " + user.getPassword());
//		System.out.println("Encoded Entered password: " + passwordEncoder.encode(user.getPassword()));
//		
//		ResponseEntity.HeadersBuilder<HeadersBuilder<B>>
		
		if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) return ResponseEntity.status(HttpStatus.OK).body(new Integer[] {userDetails.getUserId(), userDetails.getRoleId()});	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
//		 MultiValueMap<String, String> headers = new HttpHeaders();
//		 headers.add("Test", "Test");
//		 if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) return new ResponseEntity<Integer[]>(new Integer[] {userDetails.getUserId(), userDetails.getRoleId()}, headers, HttpStatus.OK); 
//		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
//	@GetMapping("/logout")
//	public ResponseEntity<String> logout() {
//		return ResponseEntity.status(HttpStatus.OK).body("Logged Out");
//	}
	
	@PostMapping("/username")
	public ResponseEntity<Boolean> checkIfUsernameAlreadyExists(@RequestBody Users user) {
		
		if (userService.getUserByUsername(user.getUsername())) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.OK).body(false);
		
	}
	
	@PatchMapping("/username")
	public ResponseEntity<Boolean> UpdateUsername(@RequestBody Users userUpdate) {
		
		if (userService.updateUsername(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PatchMapping("/email")
	public ResponseEntity<Boolean> UpdateEmail(@RequestBody Users userUpdate) {
		
		if (userService.updateEmail(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@PatchMapping("/password")
	public ResponseEntity<Boolean> UpdatePassword(@RequestBody Users userUpdate) {
		
		if (userService.updatePassword(userUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
		
		Users user = userService.getUserById(id);
		
		if (user == null) return ResponseEntity.status(404).build();
		return ResponseEntity.status(200).body(user);
		
	}

}
