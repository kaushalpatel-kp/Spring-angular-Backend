package com.Spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Spring.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	//Object findByUsername(String username);
	
}
