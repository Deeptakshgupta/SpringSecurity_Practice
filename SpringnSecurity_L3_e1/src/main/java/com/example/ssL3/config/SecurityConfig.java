package com.example.ssL3.config;



import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ssL3.config.security.filter.CustomAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

  private final CustomAuthenticationFilter customAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().anyRequest().authenticated()  // don't worry about this
        .and().build();
  }
}