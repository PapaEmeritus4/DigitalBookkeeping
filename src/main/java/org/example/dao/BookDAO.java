package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
//      РАБОТА С БД ЧЕРЕЗ Hibernate

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Book> books = session.createQuery("FROM Book ", Book.class)
                .getResultList();

        return books;
    }

    @Transactional(readOnly = true)
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        return book;
    }

    @Transactional(readOnly = false)
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();

        session.save(book);
    }

    @Transactional(readOnly = false)
    public void update(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();

        book = session.get(Book.class, id);

        session.update(book);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        session.delete(book);
    }

    public Optional<Person> getBookOwner(int id) {
        return null;
    }

    public void release(int id) {
    }

    public void appoint(int id, Person person) {
    }
}

//      РАБОТА С БД ЧЕРЕЗ JdbcTemplate

//private final JdbcTemplate jdbcTemplate;
//
//@Autowired
//public BookDAO(JdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//}
//
//public List<Book> index() {
//    return jdbcTemplate.query("SELECT * FROM Book",
//            new BeanPropertyRowMapper<>(Book.class));
//}
//
//public Book show(int id) {
//    return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
//                    new BeanPropertyRowMapper<>(Book.class))
//            .stream().findAny().orElse(null);
//}
//
//public void save(Book book) {
//    jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES (?, ?, ?)",
//            book.getTitle(), book.getAuthor(), book.getYearOfPublication());
//}
//
//public void update(int id, Book updatetBook) {
//    jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publication=? WHERE id=?",
//            updatetBook.getTitle(),
//            updatetBook.getAuthor(),
//            updatetBook.getYearOfPublication(),
//            id);
//}
//
//public void delete(int id) {
//    jdbcTemplate.update("DELETE FROM Book WHERE id=?",
//            id);
//}
//
//public Optional<Person> getBookOwner(int id) {
//    return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
//                            "WHERE Book.id=?", new Object[]{id},
//                    new BeanPropertyRowMapper<>(Person.class)).
//            stream().findAny();
//}
//
////освобождаем книгу
//public void release(int id) {
//    jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
//}
//
////назначаем книгу
//public void appoint(int id, Person person) {
//    jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", person.getId(), id);
//}