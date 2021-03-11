package com.revature.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;

@Service
public class TicketService {
	
	ReimbursementRepository reimbursementRepository;
	
	@Autowired
	public TicketService(ReimbursementRepository reimbursementRepository) {
		super();
		this.reimbursementRepository = reimbursementRepository;
	}

	public List<Reimbursement> getAllTicketsExceptById(int userId) {
		
		List<Reimbursement> ticketList = reimbursementRepository.findAll();
		ticketList = ticketList.stream().filter(ticket -> ticket.getAuthor().getUserId() != userId).collect(Collectors.toList());
		
		return ticketList;
	}

	public boolean createTicket(Reimbursement reimbursement) {
		
		try {
			reimbursement.setSubmitted(ZonedDateTime.now());
			reimbursementRepository.save(reimbursement);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateTicket(Reimbursement reimbursementUpdate) {
		
		try {
			Optional<Reimbursement> reimbursementOpt = reimbursementRepository.findById(reimbursementUpdate.getReimbId());
			
			Reimbursement reimbursement = null;
			if (reimbursementOpt.isPresent()) reimbursement = reimbursementOpt.get();
			
			reimbursement.setResolved(ZonedDateTime.now());
			reimbursement.setStatus(reimbursementUpdate.getStatus());
			reimbursement.setResolver(reimbursementUpdate.getResolver());
			
			reimbursementRepository.save(reimbursement);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Reimbursement> getTicketsById(int userId) {
		
		List<Reimbursement> ticketList = null;
		
		ticketList = reimbursementRepository.findByAuthorUserId(userId);
		
		return ticketList;
	}
	
	

}
