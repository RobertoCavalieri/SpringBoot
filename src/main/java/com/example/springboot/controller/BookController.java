package com.example.springboot.controller;

import com.example.springboot.entities.Libro;
import com.example.springboot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CRUD sobre la entidad Book
    @GetMapping("/api/books")
    public List<Libro> findAll() {
        return bookRepository.findAll();

    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Libro> findOneById(@PathVariable Long id) {

        Optional<Libro> bookOpt = bookRepository.findById(id);
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //crea un libro
    @PostMapping("/api/books")
    public ResponseEntity<Object> createBook(@RequestBody Libro libro, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recibido en la base de datos
        if (libro.getId() != null) {//verifica que el ID exista
            log.warn("Trying to create a book with id ");
            System.out.println("Trying to create a book with id ");
            return ResponseEntity.badRequest().build();
        }
        Libro result = bookRepository.save(libro);
        return ResponseEntity.ok(result);// objeto devuelto en clave primaria
    }

    //Actualizar datos de libro
    @PutMapping("/api/books")
    public ResponseEntity<Libro> update(@RequestBody Libro libro) {
        if (libro.getId() == null) {
            log.warn("Trying to update a non existent book");
            System.out.println("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();

        }
        if (!bookRepository.existsById(libro.getId())) {
            log.warn("Trying to update a non existent book");
            System.out.println("Trying to update a non existent book");
            return ResponseEntity.notFound().build();

        }
        Libro result = bookRepository.save(libro);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Libro> delete(@PathVariable Long id) {

        if (!bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
            bookRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }


@DeleteMapping("/api/books")
public ResponseEntity<Libro> deleteAll() {
    log.info("REST Request for delete all books");
            bookRepository.deleteAll();
    return ResponseEntity.noContent().build();
    }
}



//@PostMapping("/api/books")
//public Libro createBook(@RequestBody Libro libro, @RequestHeader HttpHeaders headers){
//    System.out.println(headers.get("User-Agent"));
//       return bookRepository.save(libro);
//}
//}  //crea un libro
//@PostMapping("/api/books")
//public Libro createBook(@RequestBody Libro libro){
//       return bookRepository.save(libro);