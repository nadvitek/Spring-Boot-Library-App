package com.example.cv01.dao;

import com.example.cv01.entity.Address;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryDao {
    public Library create(String name, Address address, List<Book> books);
    public Optional<Library> read(Long id);
    public List<Library> readAll();
    public void update(Library value, Long id);
    public void delete(Long id);
}
