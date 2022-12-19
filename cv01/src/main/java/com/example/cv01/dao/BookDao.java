package com.example.cv01.dao;

import com.example.cv01.entity.Author;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;
import com.example.cv01.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book create(String isbn, String name, String genre, Date publishDate,
                       List<Author> authors, List<Library> libraries, List<Publisher> publishers);
    Optional<Book> read(Long id);
    List<Book> readAll();
    void update(Book value, Long id);
    void delete(Long id);
}
