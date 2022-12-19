package com.example.cv01.controller;


import com.example.cv01.dto.BookDTO;
import com.example.cv01.dto.DTOMapper;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;
import com.example.cv01.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final static Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        LOG.info("BookController initialization");
        this.bookService = bookService;
    }

    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBookEntity(@PathVariable Long id) {
        return ResponseEntity.ok(DTOMapper.INSTANCE.bookToDTO(bookService.findBy(id)));
    }

    @Secured("ROLE_EDITOR" )
    @PatchMapping("/book/{id}/{library}")
    public ResponseEntity<BookDTO> addBookToLibrary(@PathVariable Library library, @PathVariable Long id) {
        Book book = bookService.findBy(id);

        List<Library> libraries = book.getLibraries();
        libraries.add(library);
        book.setLibraries(libraries);

        return ResponseEntity.ok(DTOMapper.INSTANCE.bookToDTO(bookService.saveBook(book)));
    }

    @Secured("ROLE_EDITOR")
    @PostMapping("/book")
    public ResponseEntity<BookDTO> createNewBook(@RequestBody Book book) {
        return ResponseEntity.ok(DTOMapper.INSTANCE.bookToDTO(bookService.saveBook(book)));
    }
}
