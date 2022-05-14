package com.generation.personalblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.personalblog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUser(String user);
}
