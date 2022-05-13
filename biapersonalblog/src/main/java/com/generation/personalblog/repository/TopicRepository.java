package com.generation.personalblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.personalblog.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

	public List<Topic> findAllByDescriptionContainingIgoreCase(String description);
}
