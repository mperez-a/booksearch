package com.example.aluracursos.booksearch;

import com.example.aluracursos.booksearch.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksearchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BooksearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
