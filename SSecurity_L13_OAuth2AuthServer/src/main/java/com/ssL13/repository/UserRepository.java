package com.ssL13.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssL13.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query(
		  """
		  SELECT u FROM User u WHERE u.username = :userName
		   """)
  Optional<User> findByUsername(String userName);
  
  // Optional<User> findUserByUserName(String userName);

}
