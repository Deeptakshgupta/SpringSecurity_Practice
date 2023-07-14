package com.ssL6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

	  @Bean
	  public UserDetailsService userDetailsService() {
	    var uds = new InMemoryUserDetailsManager();

	    var u1 = User.withUsername("bill")
	        .password(passwordEncoder().encode("12345"))
	        .authorities("read")  // --> GrantedAuthority interface
	        .build();

	    var u2 = User.withUsername("john")
	        .password(passwordEncoder().encode("12345"))
	        .authorities("write", "delete")
	        .build();

	    uds.createUser(u1);
	    uds.createUser(u2);

	    return uds;
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.httpBasic()
	        .and()
	        .authorizeRequests()
//	        .mvcMatchers(HttpMethod.GET,"/demo/**").hasAuthority("read")   /// find ANT expressions  /**
//	        .anyRequest().authenticated()
//	        .mvcMatchers("/test/test1").authenticated()
	        
	        /**  instead of MvcMatchers requestMatcher are being used from new Security version **/
	        .requestMatchers("/test/test1").authenticated()
//	        .regexMatchers("regex").authenticated()
	        .anyRequest().permitAll()
	        .and().csrf().disable()     // DON'T DO THIS IN READ-WORLD APPS
	        .build();
	  }

	  // /demo/anything/*/something   ---> /demo/anything/abc/something
	  //                                   /demo/anything/xyz/something
	}