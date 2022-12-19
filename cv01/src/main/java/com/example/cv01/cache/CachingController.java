package com.example.cv01.cache;

import com.example.cv01.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CachingController {

    private static final Log LOG = LogFactory.getLog(CachingController.class);

    private final CachingService cachingService;
    private final AddressService addressService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;
    private final LibraryService libraryService;

    @Autowired
    public CachingController(CachingService cachingService,
                             AddressService addressService,
                             AuthorService authorService,
                             BookService bookService,
                             PublisherService publisherService,
                             LibraryService libraryService) {
        this.cachingService = cachingService;
        this.addressService = addressService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.libraryService = libraryService;
    }

    @GetMapping("clearAllCaches")
    public void clearAllCaches() {
        cachingService.evictAllCaches();
    }

    @Scheduled(fixedRate = 60000)
    public void updateCache() {
        clearAllCaches();
        cachingService.getCacheNames().forEach(cacheName -> downloadToCache(cacheName));
    }

    private void downloadToCache(String cacheName) {
        switch (cacheName) {
            case "books" -> bookService.getAllBooks();
            case "addresses" -> addressService.getAllAddresses();
            case "authors" -> authorService.getAllAuthors();
            case "publishers" -> publisherService.getAllPublishers();
            case "libraries" -> libraryService.getAllLibraries();
            default -> LOG.error("Unknown cache name: " + cacheName);
        }
    }
}
