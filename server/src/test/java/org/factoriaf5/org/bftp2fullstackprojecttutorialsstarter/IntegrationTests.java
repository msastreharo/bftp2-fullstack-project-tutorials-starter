package org.factoriaf5.org.bftp2fullstackprojecttutorialsstarter;

import org.factoriaf5.org.bftp2fullstackprojecttutorialsstarter.repositories.Book;
import org.factoriaf5.org.bftp2fullstackprojecttutorialsstarter.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {

    @Autowired
    private MockMvc api;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void returnsTheExistingBooks() throws Exception {

        bookRepository.saveAll(List.of(
                new Book("Mujeres, Raza y Clase", "Angela Y. Davis"),
                new Book("El problema del trabajo", "Kathi Weeks"),
                new Book("Teoría de la Reproducción Social", "Tithi Battacharyya")
        ));


        api.perform(get("/api/books"))
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].title", equalTo("Mujeres, Raza y Clase")))
                .andExpect(jsonPath("$[0].author", equalTo("Angela Y. Davis")))
                .andExpect(jsonPath("$[1].title", equalTo("El problema del trabajo")))
                .andExpect(jsonPath("$[1].author", equalTo("Kathi Weeks")))
                .andExpect(jsonPath("$[2].title", equalTo("Teoría de la Reproducción Social")))
                .andExpect(jsonPath("$[2].author", equalTo("Tithi Battacharyya")));
    }

}
