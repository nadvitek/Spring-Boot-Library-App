package com.example.cv01.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "id", "isbn", "name", "genre", "publishDate",
                    "authors", "libraries", "publishers" })

@Entity
public class Book{

    @Id
    private Long id;
    @NotNull
    private String isbn;
    @NotNull
    private String name;
    @NotNull
    private String genre;
    @NotNull
    private Date publishDate;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy="books")
    private List<Author> authors;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Library> libraries;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Publisher> publishers;

    public Book(String isbn, String name, String genre, Date publishDate,
                List<Author> authors, List<Library> libraries, List<Publisher> publishers) {
        this.isbn = isbn;
        this.name = name;
        this.genre = genre;
        this.publishDate = publishDate;
        this.authors = authors;
        this.libraries = libraries;
        this.publishers = publishers;
    }
}
