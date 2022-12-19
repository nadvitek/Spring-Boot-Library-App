package com.example.cv01.dao;

import com.example.cv01.entity.Address;
import com.example.cv01.entity.Author;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherDao {
    public Publisher create(String name, Address address, List<Book> books, List<Author> authors);
    public Optional<Publisher> read(Long id);
    public List<Publisher> readAll();
    public void update(Publisher value, Long id);
    public void delete(Long id);
}
