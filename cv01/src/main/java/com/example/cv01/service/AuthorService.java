package com.example.cv01.service;

import com.example.cv01.entity.Author;

import java.util.List;

public interface AuthorService {
    Author findBy(Long id);
    List<Author> getAllAuthors();
    Author saveAuthor(Author author);
    void deleteAuthor(Long id);
}
