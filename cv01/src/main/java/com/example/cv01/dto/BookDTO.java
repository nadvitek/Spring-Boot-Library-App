package com.example.cv01.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDTO {

    private Long id;
    private String code;
    private String name;
    private String genre;
    private Date publishDate;

    public BookDTO(Long id, String isbn, String name, String genre, Date publishDate) {
        this.id = id;
        this.code = isbn;
        this.name = name;
        this.genre = genre;
        this.publishDate = publishDate;
    }

    public BookDTO() {

    }

}
