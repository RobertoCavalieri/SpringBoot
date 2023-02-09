package com.example.springboot.service;

import com.example.springboot.entities.Libro;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        Libro libro = new Libro(1L, "Book", "Author", 1200, 14D, LocalDate.now(), false);
        BookPriceCalculator calculator = new BookPriceCalculator();

        double price = calculator.calculatePrice(libro);
        System.out.println(price);
        assertTrue(price > 0.0);
        assertEquals(22, price);
    }
}