package com.example.cv01.dto;

import com.example.cv01.entity.*;
import org.mapstruct.Mapper;

@Mapper
public abstract class DTOMapperImpl implements DTOMapper {

    @Override
    public BookDTO bookToDTO(Book book) {
        return new BookDTO(book.getId(), book.getIsbn(), book.getName(), book.getGenre(), book.getPublishDate());
    }

    @Override
    public AddressDTO addressToDTO(Address address) {
        return new AddressDTO(address.getId(), address.getStreet(), address.getNumber(), address.getTown(), address.getPsc(), address.getLibrary(), address.getPublisher());
    }

    @Override
    public AuthorDTO authorToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getSurname(), author.getMail(), author.getBooks(), author.getPublishers());
    }

    @Override
    public LibraryDTO libraryToDTO(Library library) {
        return new LibraryDTO(library.getId(), library.getName(), library.getAddress(), library.getBooks());
    }

    @Override
    public PublisherDTO publisherToDTO(Publisher publisher) {
        return new PublisherDTO(publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getBooks(), publisher.getAuthors());
    }
}