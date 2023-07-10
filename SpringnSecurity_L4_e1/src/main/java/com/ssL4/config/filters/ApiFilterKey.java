package com.ssL4.config.filters;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssL4.config.authentication.ApiKeyAuthentication;
import com.ssL4.config.manager.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiFilterKey extends OncePerRequestFilter  {

	private final String key;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		CustomAuthenticationManager manager= new CustomAuthenticationManager(key);
		
		var requestKey = request.getHeader("x-api-key");
		if(requestKey== null || "null".equals(requestKey))
		filterChain.doFilter(request, response);
		
		ApiKeyAuthentication auth= new ApiKeyAuthentication(requestKey);
		
		try {
		var a= manager.authenticate(auth);
		if(a.isAuthenticated())
			filterChain.doFilter(request, response);
		else
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		}
		catch(AuthenticationException e)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		filterChain.doFilter(request, response);// will be called only when the user is authenticated
		
	}
	
	
}
