package com.example.cv01.controller;

import com.example.cv01.dto.AuthorDTO;
import com.example.cv01.dto.DTOMapper;
import com.example.cv01.entity.Author;
import com.example.cv01.entity.Publisher;
import com.example.cv01.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    private final static Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    public AuthorController(AuthorService authorService) {
        LOG.info("AuthorController initialization");
        this.authorService = authorService;
    }

    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getAuthorEntity(@PathVariable Long id) {
        return ResponseEntity.ok(DTOMapper.INSTANCE.authorToDTO(authorService.findBy(id)));
    }

    @Secured("ROLE_EDITOR")
    @PatchMapping("/author/{id}/{publisher}")
    public ResponseEntity<AuthorDTO> makeContractWithPublisher(@PathVariable Long id,
                                                               @PathVariable Publisher publisher) {
        Author author = authorService.findBy(id);

        List<Publisher> publishers = author.getPublishers();
        publishers.add(publisher);
        author.setPublishers(publishers);

        return ResponseEntity.ok(DTOMapper.INSTANCE.authorToDTO(authorService.saveAuthor(author)));
    }
}
