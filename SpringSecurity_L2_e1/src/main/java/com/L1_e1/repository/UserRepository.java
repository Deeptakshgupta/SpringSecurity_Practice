package com.L1_e1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.L1_e1.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//  @Query(""" 
//	Select u from User u where u.username= :username
//			""")
	
	Optional<User> findUserByUsername(String username);
}
