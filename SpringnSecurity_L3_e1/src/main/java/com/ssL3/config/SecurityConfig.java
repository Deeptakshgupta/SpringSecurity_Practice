package com.ssL3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssL3.config.security.filter.CustomAuthenticationFilter;

import lombok.AllArgsConstructor;



@Configuration
@AllArgsConstructor
public class SecurityConfig  {

	// configuring custom Authentication manager and Authentication provider
	
	private final CustomAuthenticationFilter customAuthenticationFilter;
	 	@Bean
	 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	 	{
	 	
	 		return http
	 				.addFilterAt(customAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)  // define the position where want to add the filter
	 				.authorizeRequests().anyRequest().authenticated()
	 				.and()
	 				.build();
	 	}
}
