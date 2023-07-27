package com.ssL7.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**Instead, starting from Spring Security 5.3, you should use the 
 * @EnableGlobalMethodSecurity annotation in combination with the @EnableWebSecurity annotation.
 *   **/

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@PreAuthorize @PostAuthorize @PreFilter @PostFilter
//@Secured
// @RolesAllowed
public class SecurityConfig {

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.httpBasic()
				.and().authorizeHttpRequests().anyRequest().authenticated().and()
				.build();
	}
    // all checks will be applied at mthd level using Pre/Post filters -> preAuthorize, postAuthorize
    
    @Bean 
    public PasswordEncoder passwordEncoder()
    {
    	return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
    	UserDetails u1= User.withUsername("Pulkit")
    					.password("12345")
    					.authorities("read")
    					.build();
    	
    	UserDetails u2= User.withUsername("Pulkit1")
    				.password("12345")
    				.authorities("write", "read")
    				.build();
    				
    	InMemoryUserDetailsManager uds= new InMemoryUserDetailsManager();
    	uds.createUser(u1);
    	uds.createUser(u2);
    	return uds;	
    }
    
} 