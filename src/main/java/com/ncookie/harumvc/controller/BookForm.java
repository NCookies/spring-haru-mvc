package com.ncookie.harumvc.controller;

public class BookForm {

    private String title;

    private String category;

    private int price;

    @Override
    public String toString() {
        return "BookFrom : " + title + category + price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
