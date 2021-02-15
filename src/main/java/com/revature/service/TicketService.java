package com.revature.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		
		for (int i = 0; i < ticketList.size(); i ++) {
			if (ticketList.get(i).getAuthor().equals(userId)) {
				ticketList.remove(i);
			}
		}
		
		return ticketList;
	}

	public boolean createTicket(Reimbursement reimbursement) {
		
		if (reimbursementRepository.save(reimbursement) != null) return true;
		return false;
	}

	public boolean updateTicket(int reimbId, Date resolved, int statusId) {
		
		Optional<Reimbursement> reimbursementOpt = reimbursementRepository.findById(reimbId);
		Reimbursement reimbursement = null;
		if (reimbursementOpt.isPresent()) reimbursement = reimbursementOpt.get();
		if (reimbursement != null) if (reimbursementRepository.save(reimbursement) != null) return true;		
		return false;
	}
	
	

}
