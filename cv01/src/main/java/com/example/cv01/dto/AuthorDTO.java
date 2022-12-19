package com.example.cv01.dto;

import com.example.cv01.entity.Book;
import com.example.cv01.entity.Publisher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDTO {

    private Long id;
    private String name;
    private String surname;
    private String mail;
    private List<Book> books;
    private List<Publisher> publishers;

    public AuthorDTO(Long id, String name, String surname, String mail, List<Book> books, List<Publisher> publishers) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.books = books;
        this.publishers = publishers;
    }

    public AuthorDTO() {
    }
}
