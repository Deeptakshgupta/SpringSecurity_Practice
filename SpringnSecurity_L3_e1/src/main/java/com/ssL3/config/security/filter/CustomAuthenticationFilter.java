package com.ssL3.config.security.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssL3.config.security.authentication.CustomAuthentication;
import com.ssL3.config.security.managers.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

//public class CustomAuthenticationFilter implements Filter{

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter{

	/*
	 Steps to be Followed
	 1.) create an Authentication object which is not yet Authenticated
	 2.) delegate the authenticated object to Authentication manager
	 3.) get back the Authentication from the manager
	 4.) if the object is authencticated then send request to the next Filter in the chain
	 
	 */
	
	
	
	private final CustomAuthenticationManager customAuthenticationManager;
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		// calling that filter Method from here, ensuring that it's not called more than once
		String key= String.valueOf(request.getHeader("key"));
		CustomAuthentication ca= new CustomAuthentication(false,key);
		
	var a = customAuthenticationManager.authenticate(null);
	
	if(a.isAuthenticated())
		SecurityContextHolder.getContext().setAuthentication(a);
	filterChain.doFilter(request, response);
	}
	

}
