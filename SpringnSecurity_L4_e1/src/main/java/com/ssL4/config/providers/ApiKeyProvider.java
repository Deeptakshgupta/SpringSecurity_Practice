package com.ssL4.config.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.ssL4.config.authentication.ApiKeyAuthentication;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

	private final String key;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		ApiKeyAuthentication auth= (ApiKeyAuthentication) authentication;
		if( key.equals(auth.getKey()))
			auth.setAuthenticated(true);
		else
			throw new BadCredentialsException("Something went wrong in Authentication");
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
