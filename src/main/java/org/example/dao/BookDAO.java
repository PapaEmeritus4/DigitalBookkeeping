package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{bookId},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public void update(int bookId, Book updatetBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publication=? WHERE book_id=?",
                updatetBook.getTitle(),
                updatetBook.getAuthor(),
                updatetBook.getYearOfPublication(),
                bookId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", bookId);
    }
}
