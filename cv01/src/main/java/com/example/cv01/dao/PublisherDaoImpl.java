package com.example.cv01.dao;

import com.example.cv01.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PublisherDaoImpl implements PublisherDao {
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public PublisherDaoImpl() {
        emf = Persistence.createEntityManagerFactory("publishers");
        entityManager = emf.createEntityManager();
    }

    public Publisher create(String name, Address address, List<Book> books, List<Author> authors) {
        return new Publisher(name, address, books, authors);
    }

    @Override
    public Optional<Publisher> read(Long id) {
        return Optional.ofNullable(entityManager.find(Publisher.class, id));
    }

    @Override
    public List<Publisher> readAll() {
        //Query query = entityManager.createQuery("SELECT e FROM Book");
        //return query.getResultList();
        return null;
    }

    @Override
    public void update(Publisher value, Long id) {
        Publisher publisher = read(id).get();
        if (publisher != null) {
            if (value.getName() != null) {
                publisher.setName(value.getName());
            }
            if (value.getAddress() != null) {
                publisher.setAddress(value.getAddress());
            }
            if (value.getBooks() != null) {
                publisher.setBooks(value.getBooks());
            }
            if (value.getAuthors() != null) {
                publisher.setAuthors(value.getAuthors());
            }
        }
    }

    @Override
    public void delete(Long id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
    }
}
