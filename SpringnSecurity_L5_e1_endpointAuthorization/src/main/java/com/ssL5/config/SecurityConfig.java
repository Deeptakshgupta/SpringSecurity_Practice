package com.ssL5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.httpBasic()
//		.and().authorizeRequests().anyRequest().authenticated() // end point level Authorization, this is what filter considers as rules when an endpoint is being called
// anyRequest() -> matcher Method
// authenticated() -> Authorization Rules
		.and()
		.build();
		return null;
			
/*
 matcher method + authorization Rules
 1.)which matcher method be used and how (anyRequest(), MvcMatchers(), AntMatchers, RegexMatchers())
 2.)How to apply Different Authorization Rules
 */
	}
	
	@Bean 
	public UserDetailsManager userDetailsManager()
	{
		var uds = new InMemoryUserDetailsManager();
		
		var u = User
				.withUsername("Pulkit")
				.password(passwordEncoder().encode("12345"))
				.authorities("read")
				.build();
		uds.createUser(u);
				return uds;
	}
	

	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
