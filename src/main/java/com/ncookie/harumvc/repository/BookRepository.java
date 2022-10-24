package com.ncookie.harumvc.repository;

import com.ncookie.harumvc.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final EntityManager em;

    public BookRepository(EntityManager em) {
        this.em = em;
    }

    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        Book book = em.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    public List<Book> findAll() {
        return em.createQuery("select m from Book m", Book.class)
                .getResultList();
    }

    public List<Book> findByKeyword(String keyword) {
//        TypedQuery<Book> query = em.createQuery("select m from Book m where m.title like concat('%', :keyword, '%')" +
//                "or m.category like concat('%', :keyword, '%')", Book.class);
//        query.setParameter("keyword", keyword);
//
//        return query.getResultList();

        return em.createQuery("select m from Book m where m.title like concat('%', :keyword, '%')" +
                "or m.category like concat('%', :keyword, '%')", Book.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public void remove(Long id) {
        Optional<Book> oBook = findById(id);
        oBook.ifPresent(em::remove);
    }
}
