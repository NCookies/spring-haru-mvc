package com.ncookie.harumvc.service;

import com.ncookie.harumvc.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired BookService bookService;

    @Test
    @DisplayName("책 저장 정상 동작하는지 확인")
    void save() {
        // given
        Book book = new Book();
        book.setTitle("위대한 기업에 투자하라");
        book.setCategory("경제");
        book.setPrice(30000);

        // when
        long bookId = bookService.save(book);

        // then
        Optional<Book> oBook = bookService.findOne(bookId);
        Assertions.assertThat(book.getTitle()).isEqualTo(oBook.get().getTitle());
        Assertions.assertThat(book.getCategory()).isEqualTo(oBook.get().getCategory());
        Assertions.assertThat(book.getPrice()).isEqualTo(oBook.get().getPrice());
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
    }

    @Test
    @DisplayName("키워드 검색 정상동작하는지 확인")
    void findByKeyword() {
        // given
        Book book1 = new Book();
        book1.setTitle("위대한 기업에 투자하라");
        book1.setCategory("경제");
        book1.setPrice(30000);

        Book book2 = new Book();
        book2.setTitle("은밀하게 위대하게");
        book2.setCategory("만화");
        book2.setPrice(30000);

        // when
        bookService.save(book1);
        bookService.save(book2);

        // then
        List<Book> bookFindList = bookService.findByKeyword("위대");

        for (Book findBook : bookFindList) {
            Optional<Book> oBook = bookService.findOne(findBook.getBook_id());

            assertTrue(oBook.get().getTitle().contains("위대"));
        }
    }

    @Test
    void delete() {
    }
}