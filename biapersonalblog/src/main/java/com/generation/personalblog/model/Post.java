package com.generation.personalblog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//transforma o objeto criado em uma tabela no banco de dados
@Entity

//dá um nome para a tabela no meu banco de dados
@Table(name = "tb_post")
public class Post {
	
	// define a coluna de id como chave primaria
	@Id
	// equivalente ao auto_increment no mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	// define que o campo não pode ser vazio
	@NotBlank
	// define que o campo é obrigatório
	@NotNull
	
	// define um numero minimo e maximo de caracteres no campo 
	@Size(min = 5, max = 100, message = "O campo deve ter no mínimo 5 caracteres e no máximo 100 caracteres")
	public String title;
	
	@NotBlank
	@NotNull
	@Size(min = 10, max = 500, message = "O campo deve ter no mínimo 10 caracteres e no máximo 500 caracteres")
	public String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("post")
	private Topic topic;

	@ManyToOne
	@JsonIgnoreProperties("post")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
