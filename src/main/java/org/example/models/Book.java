package org.example.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "The title of the book must not be empty")
    @Size(min = 2, max = 200, message = "Title should be between 2 and 200 characters")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "The Author of the book must not be empty")
    @Size(min = 2, max = 200, message = "Author`s name should be between 2 and 200 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Year of publication should be greater than 1500")
    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {
    }

    public Book(String name, String author, int yearOfPublication) {
        this.title = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
