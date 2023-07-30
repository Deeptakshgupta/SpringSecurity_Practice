package com.ssL13.config;

import java.util.function.Consumer;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public class CustomRedirectUriValidator implements Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {
// redirect Uri Settings
	@Override
	public void accept(OAuth2AuthorizationCodeRequestAuthenticationContext context) {
		// TODO Auto-generated method stub
		OAuth2AuthorizationCodeRequestAuthenticationToken auth = context.getAuthentication();
		RegisteredClient registeredClient = context.getRegisteredClient();
		String uri= auth.getRedirectUri();
		
		if(!registeredClient.getRedirectUris().contains(uri))
		{
			var error = new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST);
			throw new OAuth2AuthorizationCodeRequestAuthenticationException(error, null);
		}

	}

}
