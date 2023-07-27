package com.ss.config;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource.AuthenticationType;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{

	@Override
	public Authentication authenticate(Authentication authentication ) throws AuthenticationException
	{
		
		String username= authentication.getName();
		String password= String.valueOf(authentication.getCredentials());
		
		if(username.equals("Pulkit") && password.equals("12345"))
			return new UsernamePasswordAuthenticationToken(username, password,Arrays.asList());		
		else
			throw new AuthenticationCredentialsNotFoundException("Error with Credentails");
	
	}
	
	

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}

}
