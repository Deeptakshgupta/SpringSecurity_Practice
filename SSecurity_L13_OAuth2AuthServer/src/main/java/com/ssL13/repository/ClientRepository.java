package com.ssL13.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssL13.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	  @Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
	  Optional<Client> findByClientId(String clientId);
	  
	  //Optional<Client> findClientByClientId(String clientId);
}