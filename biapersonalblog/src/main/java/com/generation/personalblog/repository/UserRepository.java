package com.generation.personalblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.personalblog.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUser(String user);
}
