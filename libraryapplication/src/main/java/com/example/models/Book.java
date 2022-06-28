package com.example.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotNull(message = "Book name is bot null")
    @Size(min = 2 , max = 40 , message = "Name should be between 2 and 30 chars")
    private String book_name;
    @NotNull(message = "Author is not null")
    @Size(min = 2 , max = 40 , message = "Name should be between 2 and 30 chars")
    private String author;
    @NotNull(message = "Age is not null")
    @Min(value= 1555)

    private int book_year;

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBook_year() {
        return book_year;
    }

    public void setBook_year(int book_year) {
        this.book_year = book_year;
    }

    public Book(int book_id, String book_name, String author, int book_year) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.author = author;
        this.book_year = book_year;
    }
}
