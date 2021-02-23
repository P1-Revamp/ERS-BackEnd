package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ers_user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private int roleId;
	
	@Column(name="user_role", nullable=false, unique=true)
	private String role;
	
//	@OneToMany
//	private Users user;
//	
	public Roles(String role) {
		super();
		this.role = role;
//		this.user = user;
	}

}
