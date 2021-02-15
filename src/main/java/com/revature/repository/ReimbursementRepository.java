package com.revature.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Reimbursement;

public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {
	
//	public Optional<List<Reimbursement>> findAllByAuthor_id(int authorId);

}
