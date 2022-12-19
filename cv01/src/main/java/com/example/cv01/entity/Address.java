package com.example.cv01.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "id", "street", "number", "town", "psc", "library", "publisher" })

@Entity
public class Address{

    @Id
    private Long id;
    @Column(name="street")
    @NotNull
    private String street;
    @Column(name="number")
    @NotNull
    private String number;
    @Column(name="town")
    @NotNull
    private String town;
    @Column(name="psc")
    @NotNull
    private String psc;
    @OneToOne
    private Library library;
    @OneToOne
    private Publisher publisher;

    public Address(String street, String number, String town, String psc, Library library, Publisher publisher) {
        this.street = street;
        this.number = number;
        this.town = town;
        this.psc = psc;
        this.library = library;
        this.publisher = publisher;
    }
}
