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
@JsonPropertyOrder({ "id", "name", "address", "books", "authors"})

@Entity
public class Publisher{

    @Id
    private Long id;
    @Column(name="name")
    @NotNull
    private String name;
    @NotNull
    @OneToOne
    private Address address;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, mappedBy="publishers")
    private List<Book> books;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, mappedBy="publishers")
    private List<Author> authors;

    public Publisher(String name, Address address, List<Book> books, List<Author> authors) {
        this.name = name;
        this.address = address;
        this.books = books;
        this.authors = authors;
    }
}
