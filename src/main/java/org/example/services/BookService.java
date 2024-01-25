package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(Sort.by("yearOfPublication"));
        } else {
            return bookRepository.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
            if (sortByYear) {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfPublication"))).getContent();
            } else {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
            }
    }

    public List<Book> searchByTitle(String query) {
       return bookRepository.findByTitleStartingWith(query);
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);

        return foundBook.orElse(null);
    }

    @Transactional()
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional()
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = bookRepository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        bookRepository.save(updatedBook);
    }

    @Transactional()
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        return bookRepository.findById(id)
                .map(Book::getOwner).orElse(null);
    }

    @Transactional()
    public void release(int id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakeAt(null);
        });
    }

    @Transactional()
    public void appoint(int id, Person person) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(person);
                    book.setTakeAt(new Date());
        });
    }
}
