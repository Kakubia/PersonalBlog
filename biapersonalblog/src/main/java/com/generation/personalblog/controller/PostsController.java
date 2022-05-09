package com.generation.personalblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.personalblog.model.Posts;
import com.generation.personalblog.repository.PostsRepository;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostsController {
	
	@Autowired
	private PostsRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Posts>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Posts> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity <List <Posts>> getByTitle(@PathVariable String title) {
		return ResponseEntity.ok(repository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@PostMapping
	public ResponseEntity<Posts> post(@RequestBody Posts posts){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(posts)); 
	}
	
	@PutMapping
	public ResponseEntity<Posts> put(@RequestBody Posts posts){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(posts)); 
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

