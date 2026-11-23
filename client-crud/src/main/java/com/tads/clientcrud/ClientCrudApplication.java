package com.tads.clientcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Classe que inicia o projeto. O @SpringBootApplication liga a configuração
// automática do Spring e faz ele procurar as classes (@RestController,
// @Service, @Repository...) dentro deste pacote e dos subpacotes.
// http://localhost:8080/h2-console
// coloca jdbc:h2:mem:testdb no campo JDBC URL
@SpringBootApplication
public class ClientCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientCrudApplication.class, args);
	}

}
