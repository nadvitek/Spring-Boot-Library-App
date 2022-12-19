package com.example.cv01.controller;

import com.example.cv01.dto.DTOMapper;
import com.example.cv01.dto.LibraryDTO;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Library;
import com.example.cv01.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final static Logger LOG = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    public LibraryController(LibraryService libraryService) {
        LOG.info("LibraryController initialization");
        this.libraryService = libraryService;
    }

    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
    @GetMapping("/library/{id}")
    public ResponseEntity<LibraryDTO> getLibraryEntity(@PathVariable Long id) {
        return ResponseEntity.ok(DTOMapper.INSTANCE.libraryToDTO(libraryService.findBy(id)));
    }

    @Secured("ROLE_EDITOR")
    @PatchMapping("/library/{id}/{book}")
    public ResponseEntity<LibraryDTO> addBookToLibrary(@PathVariable Book book, @PathVariable Long id) {
        Library library = libraryService.findBy(id);

        List<Book> books = library.getBooks();
        books.add(book);
        library.setBooks(books);

        return ResponseEntity.ok(DTOMapper.INSTANCE.libraryToDTO(libraryService.saveLibrary(library)));
    }
}
