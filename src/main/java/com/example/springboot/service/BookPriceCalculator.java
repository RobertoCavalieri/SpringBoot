package com.example.springboot.service;

import com.example.springboot.entities.Libro;

public class BookPriceCalculator {

    public double calculatePrice(Libro libro){
        double price = libro.getPrice();
        if(libro.getPages() > 300){
            price += 5;
        }
        price += 3;
        return price;
    }
}
