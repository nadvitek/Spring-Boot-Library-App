package com.example.cv01.dao;

import com.example.cv01.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class LibraryDaoImpl implements LibraryDao{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public LibraryDaoImpl() {
        emf = Persistence.createEntityManagerFactory("libraries");
        entityManager = emf.createEntityManager();
    }

    public Library create(String name, Address address, List<Book> books) {
        return new Library(name, address, books);
    }

    @Override
    public Optional<Library> read(Long id) {
        return Optional.ofNullable(entityManager.find(Library.class, id));
    }

    @Override
    public List<Library> readAll() {
        //Query query = entityManager.createQuery("SELECT e FROM Book");
        //return query.getResultList();
        return null;
    }

    @Override
    public void update(Library value, Long id) {
        Library library = read(id).get();
        if (library != null) {
            if (value.getName() != null) {
                library.setName(value.getName());
            }
            if (value.getAddress() != null) {
                library.setAddress(value.getAddress());
            }
            if (value.getBooks() != null) {
                library.setBooks(value.getBooks());
            }
        }
    }

    @Override
    public void delete(Long id) {
        Library library = entityManager.find(Library.class, id);
        entityManager.remove(library);
    }
}
