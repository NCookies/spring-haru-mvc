package com.ncookie.harumvc.service;

import com.ncookie.harumvc.domain.Book;
import com.ncookie.harumvc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public long save(Book book) {
        repository.save(book);
        return book.getBook_id();
    }

    public Optional<Book> findOne(Long bookId) {
        return repository.findById(bookId);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }

    public void delete(Long bookId) {
        repository.remove(bookId);
    }
}
