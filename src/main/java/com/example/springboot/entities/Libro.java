package com.example.springboot.entities;



import jakarta.persistence.*;

//import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "libros")
public class Libro {
    //atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  title;
    private String author;
    private Integer pages;
    private Double price;
    private LocalDate date;
    private Boolean online;

    public Libro() {
    }

    public Libro(Long id, String title, String author, Integer pages, Double price, LocalDate date, Boolean online) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.date = date;
        this.online = online;
    }

    public Libro(long l, String s, String s1) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", date=" + date +
                ", online=" + online +
                '}';
    }
}
