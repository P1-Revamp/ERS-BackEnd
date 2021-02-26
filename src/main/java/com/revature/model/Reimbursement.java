package com.revature.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ers_reimbursement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_id")
	private int reimbId;
	
	@Column(name="amount", nullable=false)
	private BigDecimal amount;
	
	@Column(name="submitted", nullable=false)
	private ZonedDateTime submitted;
	
	@Column(name="resolved")
	private ZonedDateTime resolved;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="author_id", nullable=false)
	private Users author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resolver_id")
	private Users resolver;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="reimb_status_id", nullable=false)
	private ReimbStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="reimb_type_id", nullable=false)
	private ReimbType type;
	
	public Reimbursement(BigDecimal amount, ZonedDateTime submitted, ZonedDateTime resolved, String description, Users author,
			Users resolver, ReimbStatus status, ReimbType type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int reimbId, ZonedDateTime resolved, ReimbStatus status) {
		super();
		this.reimbId = reimbId;
		this.resolved = resolved;
		this.status = status;
	}
	
	
	
}
