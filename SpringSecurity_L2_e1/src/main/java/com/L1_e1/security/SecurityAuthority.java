package com.L1_e1.security;

import org.springframework.security.core.GrantedAuthority;

import com.L1_e1.entities.Authority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

	private final Authority authority;   
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority.getName();
	}

}
