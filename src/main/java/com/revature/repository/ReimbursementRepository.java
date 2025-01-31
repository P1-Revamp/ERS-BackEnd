package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Reimbursement;

public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {
	
	public List<Reimbursement> findByAuthorUserId(int authorId);

}
