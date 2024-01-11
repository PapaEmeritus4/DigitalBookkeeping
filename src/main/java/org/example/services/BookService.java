package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
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

    public Optional<Person> findByPerson(Person person) {
        return bookRepository.findByPerson(person);
    }

    public List<Book> findByPersonId(int id) {
        return bookRepository.findByPersonId(id);
    }
}
