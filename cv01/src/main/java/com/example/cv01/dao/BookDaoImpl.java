package com.example.cv01.dao;

import com.example.cv01.entity.Author;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;
import com.example.cv01.entity.Publisher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public BookDaoImpl() {
        emf = Persistence.createEntityManagerFactory("books");
        entityManager = emf.createEntityManager();
    }

    public Book create(String isbn, String name, String genre, Date publishDate,
                       List<Author> authors, List<Library> libraries, List<Publisher> publishers) {
        return new Book(isbn, name, genre, publishDate, authors, libraries, publishers);
    }

    @Override
    public Optional<Book> read(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> readAll() {
        //Query query = entityManager.createQuery("SELECT e FROM Book");
        //return query.getResultList();
        return null;
    }

    @Override
    public void update(Book value, Long id) {
        Book book = read(id).get();
        if (book != null) {
            if (value.getIsbn() != null) {
                book.setIsbn(value.getIsbn());
            }
            if (value.getName() != null) {
                book.setName(value.getName());
            }
            if (value.getGenre() != null) {
                book.setGenre(value.getGenre());
            }
            if (value.getPublishDate() != null) {
                book.setPublishDate(value.getPublishDate());
            }
            if (value.getAuthors() != null) {
                book.setAuthors(value.getAuthors());
            }
            if (value.getLibraries() != null) {
                book.setLibraries(value.getLibraries());
            }
            if (value.getPublishers() != null) {
                book.setPublishers(value.getPublishers());
            }
        }
    }

    @Override
    public void delete(Long id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
    }
}
