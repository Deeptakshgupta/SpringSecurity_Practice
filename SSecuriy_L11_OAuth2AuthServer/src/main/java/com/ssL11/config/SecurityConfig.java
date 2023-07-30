package com.ssL11.config;

import java.util.UUID;


import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {

	// Authorization Server security filter chain
	@Bean
	@Order(1)// defines the order in which components or beans gets initialized or processed.
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
			.oidc(Customizer.withDefaults());	// Initialize `OidcConfigurer` open ID connect
http.exceptionHandling(
		e-> e.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
		return http.build();
	}
	
	
	@Bean 
	@Order(2)
	public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
		// application Security Filter Chain , going with default configuration for time being
		http.formLogin()
			.and()
			.authorizeHttpRequests()
			.anyRequest().authenticated(); 	
			
		return http.build();
	}
	
	@Bean
	public UserDetailsService userdetailsService()
	{
		var uds= new InMemoryUserDetailsManager();
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
		return new BCryptPasswordEncoder();
	}

// Need Client noe ->Using RegisterClient Repository for that 
	@Bean 
	public RegisteredClientRepository registeredClientRepositoryy()
	{
		RegisteredClient r1 = RegisteredClient.withId(UUID.randomUUID().toString())
							  .clientId("client")
							  .clientSecret("cleint")
							  .scope(OidcScopes.OPENID) // we can use multiple Scopes
							  .scope(OidcScopes.PROFILE)
							  .redirectUri("http://springone.io/authorized")
							  .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
							  .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
							  .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
							  .build();
		return new InMemoryRegisteredClientRepository(r1);
	}
	
	@Bean
	public AuthorizationServerSettings authorizationServerSettings()
	{
		return AuthorizationServerSettings.builder()
				.build();
				
	}
}
