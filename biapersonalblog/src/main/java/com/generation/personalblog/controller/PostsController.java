package com.generation.personalblog.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.personalblog.model.Posts;
import com.generation.personalblog.repository.PostsRepository;

@Component
@Service
@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostsController {

	@Autowired(required=true)
	private PostsRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Posts>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
}
