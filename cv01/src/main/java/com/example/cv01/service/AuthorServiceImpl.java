package com.example.cv01.service;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import com.example.cv01.aspect.ResourceBodyFactory;
import com.example.cv01.entity.*;
import com.example.cv01.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final static Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        LOG.info("AuthorServiceImpl constructed");
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "authors.json")
    @CacheEvict(value = "authors", allEntries = true)
    public Author findBy(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for getting a Author");
            throw new IllegalArgumentException("No id for Author found.");
        }

        return authorRepository.findById(id).orElseThrow(() -> new NullPointerException("No author found for this id"));
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "authors.json")
    @CachePut("authors")
    public Author saveAuthor(Author author) {
        if (author == null) {
            LOG.error("There was no author provided for saving an Author");
            throw new NullPointerException("No author");
        }

        return authorRepository.save(author);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "authors.json")
    @CacheEvict(value = "authors", allEntries = true)
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "authors.json")
    public void deleteAuthor(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for deleting an Author");
            throw new IllegalArgumentException("No id for author found.");
        }
        authorRepository.deleteById(id);
    }

}
