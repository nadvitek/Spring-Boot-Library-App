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
@JsonPropertyOrder({ "id", "name", "address", "books" })

@Entity
public class Library{

    @Id
    private Long id;
    @Column(name="name")
    @NotNull
    private String name;
    @NotNull
    @OneToOne
    private Address address;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, mappedBy="libraries")
    private List<Book> books;

    public Library(String name, Address address, List<Book> books) {
        this.name = name;
        this.address = address;
        this.books = books;
    }
}
