package com.example.cv01.controller;

import com.example.cv01.dto.DTOMapper;
import com.example.cv01.dto.PublisherDTO;
import com.example.cv01.entity.Author;
import com.example.cv01.entity.Book;
import com.example.cv01.entity.Publisher;
import com.example.cv01.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final static Logger LOG = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    public PublisherController(PublisherService publisherService) {
        LOG.info("PublisherController initialization");
        this.publisherService = publisherService;
    }

    @Secured("ROLE_EDITOR")
    @GetMapping("/publisher/{id}")
    public ResponseEntity<PublisherDTO> getPublisherEntity(@PathVariable Long id) {
        return ResponseEntity.ok(DTOMapper.INSTANCE.publisherToDTO(publisherService.findBy(id)));
    }

    @Secured("ROLE_EDITOR")
    @PatchMapping("/publisher/{id}/{author}")
    public ResponseEntity<PublisherDTO> makeContractWithAuthor(@PathVariable Long id,
                                                               @PathVariable Author author) {
        Publisher publisher = publisherService.findBy(id);

        List<Author> authors = publisher.getAuthors();
        authors.add(author);
        publisher.setAuthors(authors);

        return ResponseEntity.ok(DTOMapper.INSTANCE.publisherToDTO(publisherService.savePublisher(publisher)));
    }

    @Secured("ROLE_EDITOR")
    @PatchMapping("/publisher/{id}/{book}")
    public ResponseEntity<PublisherDTO> publishNewBook(@PathVariable Long id,
                                                       @PathVariable Book book) {
        Publisher publisher = publisherService.findBy(id);

        List<Book> books = publisher.getBooks();
        books.add(book);
        publisher.setBooks(books);

        return ResponseEntity.ok(DTOMapper.INSTANCE.publisherToDTO(publisherService.savePublisher(publisher)));
    }
}
