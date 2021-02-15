package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ers_reimbursement_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReimbStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="status_id")
	private int statusId;
	
	@Column(name="status", nullable=false, unique=true)
	private String status;
	
//	@ManyToOne
//	private Reimbursement reimbursement;
	
	public ReimbStatus(String status) {
		super();
		this.status = status;
//		this.reimbursement = reimbursement;
	}

}
