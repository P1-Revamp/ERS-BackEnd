package com.revature.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.ReimbStatus;
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

//	public List<Reimbursement> getTicketsById(int authorId) {
//		
//		Optional<List<Reimbursement>> reimbursementOpt = reimbursementRepository.findAllByAuthor_id(authorId);
//		List<Reimbursement> reimbursementList = null;
//		if (reimbursementOpt.isPresent()) reimbursementList = reimbursementOpt.get();
//		
//		return reimbursementList;
//
//	}

	public List<Reimbursement> getAllTicketsExceptById(int userId) {
		
		List<Reimbursement> ticketList = reimbursementRepository.findAll();
		ticketList = ticketList.stream().filter(ticket -> ticket.getAuthor().getUserId() != userId).collect(Collectors.toList());
		
//		for (int i = 0; i < ticketList.size(); i ++) {
//			if (ticketList.get(i).getAuthor().equals(userId)) {
//				ticketList.remove(i);
//			}
//		}
		
		return ticketList;
	}

	public boolean createTicket(Reimbursement reimbursement) {
		
		try {
			reimbursementRepository.save(reimbursement);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateTicket(int reimbId, ZonedDateTime resolved, int statusId) {
		
		try {
			Optional<Reimbursement> reimbursementOpt = reimbursementRepository.findById(reimbId);
			
			Reimbursement reimbursement = null;
			if (reimbursementOpt.isPresent()) reimbursement = reimbursementOpt.get();
			
			reimbursement.setResolved(resolved);
			reimbursement.setStatus(new ReimbStatus(statusId, ""));
			reimbursementRepository.save(reimbursement);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

}
