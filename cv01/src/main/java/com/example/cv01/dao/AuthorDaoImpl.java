package com.example.cv01.dao;

import com.example.cv01.entity.Author;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;
import com.example.cv01.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public AuthorDaoImpl() {
        emf = Persistence.createEntityManagerFactory("authors");
        entityManager = emf.createEntityManager();
    }

    public Author create(String name, String surname, String mail, List<Book> books, List<Publisher> publishers) {
        return new Author(name, surname, mail, books, publishers);
    }

    @Override
    public Optional<Author> read(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> readAll() {
        //Query query = entityManager.createQuery("SELECT e FROM Book");
        //return query.getResultList();
        return null;
    }

    @Override
    public void update(Author value, Long id) {
        Author author = read(id).get();
        if (author != null) {
            if (value.getName() != null) {
                author.setName(value.getName());
            }
            if (value.getSurname() != null) {
                author.setSurname(value.getSurname());
            }
            if (value.getMail() != null) {
                author.setMail(value.getMail());
            }
            if (value.getBooks() != null) {
                author.setBooks(value.getBooks());
            }
            if (value.getPublishers() != null) {
                author.setPublishers(value.getPublishers());
            }
        }
    }

    @Override
    public void delete(Long id) {
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
    }
}
