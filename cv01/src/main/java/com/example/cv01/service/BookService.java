package com.example.cv01.service;

import com.example.cv01.entity.Book;

import java.util.List;

public interface BookService {
    Book findBy(Long id);
    List<Book> getAllBooks();
    Book saveBook(Book book);
    void deleteBook(Long id);
}
