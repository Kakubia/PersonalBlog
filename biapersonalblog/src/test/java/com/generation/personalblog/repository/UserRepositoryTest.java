package com.generation.personalblog.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.personalblog.model.User;


/*indicando que a classe UsuarioRepositoryTest é uma classe de test, 
 * que vai rodar em uma porta aleatoria a cada teste realizado 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 

/*cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o 
ciclo de vida da classe(será executado e resetado após o uso)
*/ 
@TestInstance(TestInstance.Lifecycle.PER_CLASS) 

public class UserRepositoryTest {
	
	@Autowired private UserRepository repository;
	
	@BeforeAll
	void start() {
		repository.save(new User(0L,"Maiar","isadora@gmail.com","51 e pinga","https://i.imgur.com/FETvs2O.jpg"));
		
		repository.save(new User(0L,"Michael","michaeltrimundial@gmail.com","nunca fui rebaixado","https://i.imgur.com/FETvs2O.jpg"));
		
		repository.save(new User(0L,"Brocco","brocco@gmail.com","broccolis","https://i.imgur.com/FETvs2O.jpg"));
		
		repository.save(new User(0L,"Mayara","will31smith@gmail.com","cenoura","https://i.imgur.com/FETvs2O.jpg"));
	}

	@Test
	@DisplayName("Teste que retorna 1 usuario")
	public void retornaUmUsuario() { 
		Optional<User> user = repository.findByUser("isadora@gmail.com");
        assertTrue(User.get().getUser().equals("isadora@gmail.com"));
    }

	@Test
	@DisplayName("Teste que retorna 3 usuarios")
	public void retornaTresUsuarios() {
		List<User> listaDeUsuarios = repository.findAllByNameContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getUser().equals("Maiar da Silva"));
		assertTrue(listaDeUsuarios.get(1).getUser().equals("Brocco da Silva"));
		assertTrue(listaDeUsuarios.get(2).getUser().equals("Rafael Silva"));
	}
	
    @AfterAll
    public void end() {
        repository.deleteAll();
    }
}
