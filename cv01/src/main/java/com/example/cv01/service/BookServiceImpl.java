package com.example.cv01.service;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import com.example.cv01.aspect.ResourceBodyFactory;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;
import com.example.cv01.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final static Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        LOG.info("BookServiceImpl constructed");
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "books.json")
    @CacheEvict(value = "books", allEntries = true)
    public Book findBy(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for getting a Book");
            throw new IllegalArgumentException("No id for Book found.");
        }

        return bookRepository.findById(id).orElseThrow(() -> new NullPointerException("No book found for this id"));
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "books.json")
    @CachePut({"books", "libraries"})
    public Book saveLibrary(Long id, Library library) {
        Book book = findBy(id);

        if (library == null) {
            throw new IllegalArgumentException("No library in adding library to book");
        }

        return bookRepository.save(book);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "books.json")
    @CachePut("books")
    public void createBook(String name, String isbn, String genre) {
        Book book = new Book();
        book.setPublishers(new ArrayList<>());
        book.setAuthors(new ArrayList<>());
        book.setGenre(genre);
        book.setPublishDate(new Date());
        book.setIsbn(isbn);
        book.setLibraries(new ArrayList<>());
        book.setName(name);
        bookRepository.save(book);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "books.json")
    @CachePut("books")
    public Book saveBook(Book book) {
        if (book == null) {
            LOG.error("There was no book provided for saving a Book");
            throw new NullPointerException("No Book");
        }

        return bookRepository.save(book);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "books.json")
    @CacheEvict(value = "books", allEntries = true)
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "books.json")
    public void deleteBook(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for deleting a Book");
            throw new IllegalArgumentException("No id for book found.");
        }

        bookRepository.deleteById(id);
    }

}
