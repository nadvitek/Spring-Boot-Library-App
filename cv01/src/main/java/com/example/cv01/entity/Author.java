package com.example.cv01.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "id", "name", "surname", "mail", "books", "publishers"})

@Entity
public class Author{

    @Id
    private Long id;
    @Column(name="name")
    @NotNull
    private String name;
    @Column(name="surname")
    @NotNull
    private String surname;
    @Column(name="mail", unique = true)
    @NotNull
    private String mail;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> books;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Publisher> publishers;

    public Author(String name, String surname, String mail, List<Book> books, List<Publisher> publishers) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.books = books;
        this.publishers = publishers;
    }
}
