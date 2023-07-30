package com.ssL13.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssL13.entities.User;
import com.ssL13.model.SecurityUser;
import com.ssL13.repository.UserRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	// we can also directly use the @Autowired Annotation
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  
  //using map function provided by the Optional Class and then converting it into SecurityUser which is implementing the UserDetails  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
   
    return user.map(SecurityUser :: new).orElseThrow(() -> new UsernameNotFoundException(":("));
//    return user.map(u-> new SecurityUser(u)).orElseThrow(()-> new UsernameNotFoundException(":(")); // without method reference, using lambda expression instead 
  }
}