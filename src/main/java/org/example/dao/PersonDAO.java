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
public class PersonDAO {
//      РАБОТА С БД ЧЕРЕЗ Hibernate

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("SELECT Person from Person", Person.class)
                .getResultList();

        return people;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        return person;
    }

    @Transactional(readOnly = false)
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.save(person);
    }

    @Transactional(readOnly = false)
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();

        person = session.get(Person.class, id);

        session.update(person);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        session.delete(person);
    }

}
//      РАБОТА С БД ЧЕРЕЗ JdbcTemplate

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person",
//                new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name, year_of_birth) VALUES (?, ?)",
//                person.getName(), person.getYearOfBirth());
//    }
//
//    public void update(int id, Person updatetPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, year_of_birth=? WHERE id=?",
//                updatetPerson.getName(),
//                updatetPerson.getYearOfBirth(),
//                id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public Optional<Person> getPersonByName(String name) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).
//                stream().findAny();
//    }
//
//    public List<Book> getBooksByPersonId(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }

