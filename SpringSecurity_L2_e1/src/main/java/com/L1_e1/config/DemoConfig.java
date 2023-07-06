package com.L1_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.L1_e1.services.JpaUserDetailsServices;

@Configuration
public class DemoConfig {

	/* not good to use this kind of code,
	 * instead of creating a bean here we will user Service Annotation over the service Class 
	 * @Bean
	public UserDetailsService userDetailService()
	{
		// we have to pass User Repository Object in it's args.
		return new JpaUserDetailsServices();
		// passing that is not recommended 
	}*/
	
	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
}
