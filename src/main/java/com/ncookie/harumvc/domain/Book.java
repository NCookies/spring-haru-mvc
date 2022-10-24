package com.ncookie.harumvc.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners({AuditingEntityListener.class})
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    private String title;

    private String category;

    private int price;

    @CreatedDate
    private LocalDateTime insert_date;

    public Long getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getInsert_date() {
        return insert_date;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setInsert_date(LocalDateTime insert_date) {
        this.insert_date = insert_date;
    }
}
