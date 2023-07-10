package com.ssL4.config.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.ssL4.config.providers.ApiKeyProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{

	private final String key;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		var provider = new ApiKeyProvider(key);
		if(provider.supports(authentication.getClass()))
			return provider.authenticate(authentication);
		
		return authentication;
	}

}
