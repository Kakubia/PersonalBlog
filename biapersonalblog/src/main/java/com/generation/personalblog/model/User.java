package com.generation.personalblog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name="tb_user") 
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	@NotBlank
	@Size(min=2, max=100)
	private String name; 
	@NotNull
	@Email(message = "O usuário deve ter um email valido ex:maria@email.com")
	@Size(min=5, max=100)
	private String user; 
	@NotBlank
	@Size(min=5, max=100)
	private String password;
	
	private String photo;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user") 
	private List<Post> post;
	
	
	
	public User(Long id, String name, String user, String password, String photo) {
		
		this.id = id;
		this.name = name;
		this.user = user;
		this.password = password;
		this.photo = photo;
	}
	
	public User() {};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

}
