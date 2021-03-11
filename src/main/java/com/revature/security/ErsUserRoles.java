package com.revature.security;

import static com.revature.security.ErsUserAuthorities.EMPLOYEE_READ;
import static com.revature.security.ErsUserAuthorities.EMPLOYEE_WRITE;
import static com.revature.security.ErsUserAuthorities.TICKET_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErsUserRoles {
	EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE)),
	FINANCIAL_MANAGER(Sets.newHashSet(TICKET_WRITE));
	
	private final Set<ErsUserAuthorities> permissions;
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}

}
