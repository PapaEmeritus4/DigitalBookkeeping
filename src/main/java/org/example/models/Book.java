package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;

    @NotEmpty
    @Size(min = 2, max = 200, message = "Title should be between 2 and 200 characters")
    private String title;

    @NotEmpty
    @Size(min = 2, max = 200, message = "Author`s name should be between 2 and 200 characters")
    private String author;

    @Min(value = 1500, message = "Year of publication should be greater than 1500")
    private int yearOfPublication;

    public Book() {
    }

    public Book(int bookId, String name, String author, int yearOfPublication) {
        this.bookId = bookId;
        this.title = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}