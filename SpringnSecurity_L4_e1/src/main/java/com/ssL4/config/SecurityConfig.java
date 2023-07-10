package com.ssL4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ssL4.config.filters.ApiFilterKey;

@Configuration
public class SecurityConfig {
	
	@Value("${secret.key}")
	private String key;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
	 return http.httpBasic()
			 .and()
			 .addFilterBefore(new ApiFilterKey(key), BasicAuthenticationFilter.class)
			 .authorizeRequests().anyRequest().authenticated()	
//			 .and().authenticationManager(null) -> or by adding a bean of type Authentication Manager
//			 .and().authenticationProvider(null) -> doesn't override the Authentication Provider,but adds one more to the Collection of already present Authentication providers
			 .and().build();
	}
}