package com.L1_e1.entities;

import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//we don't want to assign 2 responsibilities in the same class
// we created a wraaper -> security Authority in Security package

@Entity
@Getter
@Setter
@Table(name = "users") // user table may get Clashed, not recommended to be used 
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="user_authorities" ,joinColumns=@JoinColumn(name= "user_id"),inverseJoinColumns= @JoinColumn(name ="authortiy_id"))
	//join column represents the column name in the DB table to be mapped
	private Set<Authority> authorites;
}
