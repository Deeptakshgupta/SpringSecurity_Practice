package com.L1_e1.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.L1_e1.repository.UserRepository;
import com.L1_e1.security.SecurityUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JpaUserDetailsServices implements UserDetailsService{

//	@Autowired
//	public  UserRepository userRepository;
	
	private final UserRepository userRepository;// requires All Args Constructor to be added -> 
	//final variable needs to initialized at the point of declaration
	
	// Runtime Exception -> UsernameException extends -> AuthenticationException
	@Override
	public UserDetails loadUserByUsername(String username) {
	
		
		var u= userRepository.findUserByUsername(username);
		return u.map(SecurityUser :: new)
				.orElseThrow(()-> new UsernameNotFoundException("user not found with name:"+username));
	}

}
