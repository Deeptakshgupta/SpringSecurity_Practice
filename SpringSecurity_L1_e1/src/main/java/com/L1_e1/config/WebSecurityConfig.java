package com.L1_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {

// Using UserDetailsService provided to manage the User name and password with Details stored in UsreDetails object 	
	
	@Bean 
	public UserDetailsService userDetailsService()
	{
		var uds= new InMemoryUserDetailsManager();//UserDetailsSrevice Object Declare 
		
		// User Object creation 
		var u1= User.withUsername("Pulkit")
				.password("12345")
				.authorities("read")
				.build();
		
		uds.createUser(u1);
		
		return uds;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		// this is the Simplest Encoder which provides no Encoding at All
		// is  being used just for demo purpose, not to used for Encoding in any where else  
		
		return NoOpPasswordEncoder.getInstance();
	}
}
