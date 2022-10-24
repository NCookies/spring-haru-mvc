package com.ncookie.harumvc;

import com.ncookie.harumvc.domain.Book;
import com.ncookie.harumvc.repository.BookRepository;
import com.ncookie.harumvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

public class SpringConfigure {
    private EntityManager em;

    @Autowired
    public SpringConfigure(EntityManager em) {
        this.em = em;
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository(em);
    }
}
