package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasAuthority('ticket:read')")
	@GetMapping("/{userId}")
	public ResponseEntity<List<Reimbursement>> getTicketsById(@PathVariable int userId) {
		
		List<Reimbursement> ticketList = ticketService.getTicketsById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ticketList);
	}
	
	@PreAuthorize("hasRole('FINANCIAL_MANAGER')")
	@GetMapping("/review/{userId}")
	public ResponseEntity<List<Reimbursement>> getAllTicketsExceptById(@PathVariable int userId) {
		
		List<Reimbursement> ticketList = ticketService.getAllTicketsExceptById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ticketList);
	}
	
	@PreAuthorize("hasAuthority('ticket:write')")
	@PostMapping
	public ResponseEntity<Boolean> createTicket(@RequestBody Reimbursement reimbursement) {
		
		if (ticketService.createTicket(reimbursement)) return ResponseEntity.status(HttpStatus.CREATED).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}

	@PreAuthorize("hasRole('FINANCIAL_MANAGER')")
	@PatchMapping
	public ResponseEntity<Boolean> updateTicket(@RequestBody Reimbursement reimbursementUpdate) {
		
		if (ticketService.updateTicket(reimbursementUpdate)) return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		
	}
	
}
