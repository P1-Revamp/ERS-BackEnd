package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ers_reimbursement_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReimbType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="type_id")
	private int typeId;
	
	@Column(name="type", nullable=false, unique=true)
	private String type;

	public ReimbType(String type) {
		super();
		this.type = type;
	}
	

	
}
