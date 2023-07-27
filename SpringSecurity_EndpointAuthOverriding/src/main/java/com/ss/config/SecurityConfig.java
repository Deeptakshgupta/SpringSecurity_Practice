package com.ss.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/** All endpoints of the application need not to be secured, for those we need to choose diffrent authorization rules 
 * to do so we extend WebSecurityConfigurerAdapter
 * allows us to override the configure method
 * 
 * We override this method from the WebSecurityConfigurerAdapter class
and use its parameter of type AuthenticationManagerBuilder to set both the
UserDetailsService and the PasswordEncoder
 * **/
 

/** WEbConfiguratorAdaptor got deprecated since spring 5.7.0 
 @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	// provides 3 mthds to override 
	//
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic()
			.and()
			.authorizeHttpRequests().anyRequest().authenticated()
			.and().build();
	}
	// can  configure userDetails and password Encoder in one file only but recommended to use separately 
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{
		var uds= new InMemoryUserDetailsManager();
		UserDetails user1= User.withUsername("Pulkit")
					.password("12345")
					.authorities("read")
					.build();
			uds.createUser(user1);
		auth.userDetailsService(uds)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
//		auth.authenticationProvider(customAuthProvider);
	}
	
}
**/



/** using custom Authentication provider **/ 

@Configuration
public class SecurityConfig// extends WebSecurityConfigurerAdapter
{
	@Autowired
	public CustomAuthenticationProvider authProvider;
	
//	@Override
	public void configure(AuthenticationManagerBuilder auth)
	{
		auth.authenticationProvider(authProvider);
	}
//	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic();
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
	}
}

