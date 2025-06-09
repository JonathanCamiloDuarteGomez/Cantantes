package com.camilo.cantantes.cantantes;

import com.camilo.cantantes.cantantes.Repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import principal.Principal;

import java.nio.channels.MulticastChannel;

@SpringBootApplication
public class CantantesApplication implements CommandLineRunner {
	//Las inyecciones de dependencias solo se puede llamar en una clase creada por el propio Spring
	@Autowired//Inyeccion de dependencias
	private MusicaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CantantesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.verMenu();
	}
}
