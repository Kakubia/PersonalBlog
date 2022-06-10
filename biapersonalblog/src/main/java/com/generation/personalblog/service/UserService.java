package com.generation.personalblog.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.personalblog.model.User;
import com.generation.personalblog.model.UserLogin;
import com.generation.personalblog.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> registerUser(User user) {

		if (userRepository.findByUser(user.getUser()).isPresent())
			return Optional.empty();

		user.setPassword(encryptPassword(user.getPassword()));

		return Optional.of(userRepository.save(user));

	}

	public Optional<User> updateUser(User user) {

		if (userRepository.findById(user.getId()).isPresent()) {

			Optional<User> findUser = userRepository.findByUser(user.getUser());

			if (findUser.isPresent()) {
				if (findUser.get().getId() != user.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}

			user.setPassword(encryptPassword(user.getPassword()));

			return Optional.of(userRepository.save(user));
		}

		return Optional.empty();
	}

	public Optional<UserLogin> authenticateUser(Optional<UserLogin> userLogin) {

		Optional<User> user = userRepository.findByUser(userLogin.get().getUser());

		if (user.isPresent()) {
			if (comparePasswords(userLogin.get().getPassword(), user.get().getPassword())) {

				userLogin.get().setToken(generateBasicToken(userLogin.get().getUser(), userLogin.get().getPassword()));
				userLogin.get().setId(user.get().getId());
				userLogin.get().setName(user.get().getName());
				userLogin.get().setPassword(user.get().getPassword());
				userLogin.get().setUser(user.get().getUser());
				userLogin.get().setPhoto(user.get().getPhoto());
				userLogin.get().setType(user.get().getType());
				
				return userLogin;

			}
		}

		return Optional.empty();

	}

	private String encryptPassword(String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(password);

	}

	private boolean comparePasswords(String passwordTyped, String passwordDB) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(passwordTyped, passwordDB);

	}

	private String generateBasicToken(String email, String password) {

		String tokenBase = email + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}