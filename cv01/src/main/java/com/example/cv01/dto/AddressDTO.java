package com.example.cv01.dto;

import com.example.cv01.entity.Library;
import com.example.cv01.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor

@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String town;
    private String psc;
    private Library library;
    private Publisher publisher;
}
