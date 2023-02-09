package com.example.springboot.controller;

import com.example.springboot.entities.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class BookControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("Http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void hello() {
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/hola", String.class);
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola Mundo", response.getBody());

    }
    @Test
    void findAll() {
        ResponseEntity<Libro[]> response =
        testRestTemplate.getForEntity("/api/books", Libro[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Libro> libros = Arrays.asList(response.getBody());
        System.out.println(libros.size());
    }

    @Test
    void findOneById() {
        ResponseEntity<Libro> response =
                testRestTemplate.getForEntity("/api/books/1", Libro.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createBook() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                        {
                        "title": "October country",
                        "author": "Ray Bradbury",
                        "pages": 212,
                        "price": 15.99,
                        "date": "1972-08-01",
                        "online": true
                        }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Libro> response = testRestTemplate.exchange
                ("/api/books", HttpMethod.POST, request, Libro.class);
        Libro result = response.getBody();
        assert result != null;
        assertEquals(1L, result.getId());
        assertEquals("October country", result.getTitle());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}