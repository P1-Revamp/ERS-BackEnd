package com.revature.security;

import static com.revature.security.ErsUserAuthorities.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErsUserRoles {
	EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE, TICKET_READ, TICKET_WRITE)),
	FINANCIAL_MANAGER(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE, TICKET_READ, ALL_TICKET_READ, TICKET_WRITE, ALL_TICKET_WRITE));
	
	private final Set<ErsUserAuthorities> permissions;
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissionSet = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		
		permissionSet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissionSet;
	}

}
