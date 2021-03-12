package com.revature.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErsUserAuthorities {
	EMPLOYEE_READ("employee:read"),
	ALL_EMPLOYEE_READ("all-employee:read"),
	
	EMPLOYEE_WRITE("employee:write"),
	ALL_EMPLOYEE_WRITE("all-employee:write"),
	
	TICKET_READ("ticket:read"),
	ALL_TICKET_READ("all-ticket:read"),
	
	TICKET_WRITE("ticket:write"),
	ALL_TICKET_WRITE("all-ticket:write");
	
	private final String permission;

}
