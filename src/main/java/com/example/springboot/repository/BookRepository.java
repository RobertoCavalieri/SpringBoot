package com.example.springboot.repository;

import com.example.springboot.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface BookRepository extends JpaRepository<Libro, Long> {
}
