package com.example.cv01.dao;

import com.example.cv01.entity.Author;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface AuthorDao{
    public Author create(String name, String surname, String mail, List<Book> books, List<Publisher> publishers);
    public Optional<Author> read(Long id);
    public List<Author> readAll();
    public void update(Author value, Long id);
    public void delete(Long id);
}
