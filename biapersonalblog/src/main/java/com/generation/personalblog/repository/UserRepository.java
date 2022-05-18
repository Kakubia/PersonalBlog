package com.generation.personalblog.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.personalblog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUser(String user);
	
	public List<User> findAllByNameContainingIgnoreCase(@Param("name") String name);
	
}


