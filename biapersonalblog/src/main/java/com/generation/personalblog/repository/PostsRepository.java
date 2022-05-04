package com.generation.personalblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.personalblog.model.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{

	public List<Posts> findAllByTitleContainingIgnoreCase (String title);
}
