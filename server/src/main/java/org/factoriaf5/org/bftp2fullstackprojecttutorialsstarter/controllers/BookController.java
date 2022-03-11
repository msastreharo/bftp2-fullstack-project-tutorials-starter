package org.factoriaf5.org.bftp2fullstackprojecttutorialsstarter.controllers;

import org.factoriaf5.org.bftp2fullstackprojecttutorialsstarter.repositories.Book;
import org.factoriaf5.org.bftp2fullstackprojecttutorialsstarter.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;

@RestController
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @PostConstruct
    @Profile("!prod")
    private void loadSampleData() {
        bookRepository.saveAll(List.of(
                new Book("Mujeres, Raza y Clase", "Angela Y. Davis"),
                new Book("El problema del trabajo", "Kathi Weeks"),
                new Book("Teoría de la Reproducción Social", "Tithi Battacharyya")
        ));
    }
}
