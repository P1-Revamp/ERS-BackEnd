package com.revature.controller;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Reimbursement;
import com.revature.service.TicketService;

@RestController
@RequestMapping(value="/reimbursement")
@CrossOrigin
public class TicketController {
	
	TicketService ticketService;
	
	@Autowired
	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}
	
	@GetMapping("/review/{userId}")
	public ResponseEntity<List<Reimbursement>> getAllTicketsExceptById(@PathVariable int userId) {
		List<Reimbursement> ticketList = ticketService.getAllTicketsExceptById(userId);
		return ResponseEntity.status(200).body(ticketList);
	}
	
//	@GetMapping("/{userId}")
//	public ResponseEntity<List<Reimbursement>> getTicketsById(@PathVariable int userId) {
//		
//		List<Reimbursement> ticketList = ticketService.getTicketsById(userId);
//		
//		if (ticketList != null) return ResponseEntity.status(200).body(ticketList);		
//		return null;
//		
//	}
	
	@PostMapping
	public ResponseEntity createTicket(@RequestBody Reimbursement reimbursement) {
		
		reimbursement.setSubmitted(ZonedDateTime.now());
		
		if (ticketService.createTicket(reimbursement)) return ResponseEntity.status(201).build();
		return ResponseEntity.status(500).build();
		
	}

	@PatchMapping
	public ResponseEntity updateTicket(@RequestBody int reimbId, Date resolved, int statusId) {
		
		if (ticketService.updateTicket(reimbId, resolved, statusId)) return ResponseEntity.status(200).build();
		return ResponseEntity.status(500).build();
		
	}
	
}
