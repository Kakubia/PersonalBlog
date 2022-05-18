package com.generation.personalblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.personalblog.model.Topic;

@Repository

public interface TopicRepository extends JpaRepository<Topic, Long>{

	public List<Topic> findAllByDescriptionContainingIgnoreCase(String description);
}
