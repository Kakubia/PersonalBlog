package com.generation.personalblog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.personalblog.model.User;
import com.generation.personalblog.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UserService usuarioService;

	@Test
	@Order(1)
	@DisplayName("Cadastrar Um Usuário")
	public void mustCreateOneUser() {
		HttpEntity<User> requisitionBody = new HttpEntity<User>(new User(0L, "Maria Santos",
				"maria_santos@email.com.br", "123465278", "https://i.imgur.com/JR7kUFU.jpg"));
		
		ResponseEntity<User> answerBody = testRestTemplate.exchange("/user/register", HttpMethod.POST,
				requisitionBody, User.class);
		
		assertEquals(HttpStatus.CREATED, answerBody.getStatusCode());
		assertEquals(requisitionBody.getBody().getName(), answerBody.getBody().getName());
		assertEquals(requisitionBody.getBody().getUser(), answerBody.getBody().getUser());
	}

	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void mustNotDuplicateUser() {

		usuarioService.registerUser(new User(0L, "Maria da Silva", "maria_silva@email.com.br", "13465278",
				"https://i.imgur.com/T12NIp9.jpg"));

		HttpEntity<User> requisitionBody = new HttpEntity<User>(new User(0L, "Maria da Silva",
				"maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		ResponseEntity<User> answerBody = testRestTemplate.exchange("/user/register", HttpMethod.POST,
				requisitionBody, User.class);

		assertEquals(HttpStatus.BAD_REQUEST, answerBody.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Atualizar um Usuário")
	public void mustUpdateOneUser() {
		
		HttpEntity<User> requisitionBody = new HttpEntity<User>(new User(0L, 
				"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));
		
		Optional<User> registeredUser = usuarioService.registerUser(new User(0L, "Juliana Andrews",
				"juliana_andrews@email.com.br", "juliana123", "https://i.imgur.com/yDRVeK7.jpg"));

		User userUpdate = new User(registeredUser.get().getId(), "Juliana Andrews Ramos",
				"juliana_ramos@email.com.br", "juliana123", "https://i.imgur.com/yDRVeK7.jpg");

		ResponseEntity<User> answerBody = testRestTemplate.withBasicAuth("root", "root")
				.exchange("/user/update", HttpMethod.PUT, requisitionBody, User.class);

		assertEquals(HttpStatus.OK, answerBody.getStatusCode());

		assertEquals(requisitionBody.getBody().getName(), answerBody.getBody().getName());

		assertEquals(requisitionBody.getBody().getUser(), answerBody.getBody().getUser());
	}

	@Test
	@Order(4)
	@DisplayName("Listar todos os Usuários")
	public void mustShowAllUsers() {

		usuarioService.registerUser(new User(0L, "Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123",
				"https://i.imgur.com/5M2p5Wb.jpg"));

		usuarioService.registerUser(new User(0L, "Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123",
				"https://i.imgur.com/Sk5SjWE.jpg"));

		ResponseEntity<String> answer = testRestTemplate.withBasicAuth("root", "root").exchange("/user/all",
				HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, answer.getStatusCode());
	}

	@Test
	@Order(5)
	@DisplayName("Listar Um Usuário Específico")
	public void mustListJustOneUser() {
		Optional<User> searchUser = usuarioService.registerUser(new User(0L, "Laura Santolia",
				"laura_santolia@email.com.br", "laura12345", "https://i.imgur.com/EcJG8kB.jpg"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root", "root")
				.exchange("/user/" + searchUser.get().getId(), HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}
}