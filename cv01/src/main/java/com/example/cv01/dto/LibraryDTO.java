package com.example.cv01.dto;

import com.example.cv01.entity.Address;
import com.example.cv01.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibraryDTO {

    private Long id;
    private String name;
    private Address address;
    private List<Book> books;

    public LibraryDTO(Long id, String name, Address address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public LibraryDTO() {
    }
}
