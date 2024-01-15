package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BookRepository;
import org.example.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findWithPagination(Integer page, Integer booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage,
                Sort.by("year_of_publication")));
    }

    public List<Book> sortedByYear() {
        return bookRepository.findAll(Sort.by("year_of_publication"));
    }


    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);

        return foundBook.orElse(null);
    }

    @Transactional(readOnly = false)
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return bookRepository.findById(id)
                .map(Book::getOwner);
    }

    @Transactional(readOnly = false)
    public void release(int id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
        });
    }

    @Transactional(readOnly = false)
    public void appoint(int id, Person person) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(person);
        });
    }
}
