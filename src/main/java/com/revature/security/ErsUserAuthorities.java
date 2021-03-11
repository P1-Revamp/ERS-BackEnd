package com.revature.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErsUserAuthorities {
	EMPLOYEE_READ("employee:read"),
	EMPLOYEE_WRITE("employee:write"),
	TICKET_READ("ticket:read"),
	TICKET_WRITE("ticket:write");
	
	private final String permission;

}
