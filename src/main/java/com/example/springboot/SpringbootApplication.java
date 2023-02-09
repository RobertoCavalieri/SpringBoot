package com.example.springboot;

import com.example.springboot.entities.Libro;
import com.example.springboot.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);
		Libro libro = new Libro(null, "Crónicas Marcianas", "Ray Bradbury", 254, 19.99D, LocalDate.of(1956, 10, 1), true);
		Libro libro2 = new Libro(null, "October country", "Ray Bradbury", 212, 15.99D, LocalDate.of(1972, 8, 1), true);
		System.out.println("Libros en base de datos: " + repository.findAll().size());
		//guardar un elemento
		repository.save(libro);
		repository.save(libro2);

		//contar elementos
		System.out.println("Libros en base de datos: " + repository.findAll().size());

		//borrar un elemento
		//repository.deleteById(1L);
		System.out.println("Libros en base de datos: " + repository.findAll().size());



	}

}
