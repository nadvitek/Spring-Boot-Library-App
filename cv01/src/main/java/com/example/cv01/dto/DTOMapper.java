package com.example.cv01.dto;

import com.example.cv01.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    @Mapping(source="isbn", target="code")
    BookDTO bookToDTO(Book book);
    AddressDTO addressToDTO(Address address);
    AuthorDTO authorToDTO(Author author);
    LibraryDTO libraryToDTO(Library library);
    PublisherDTO publisherToDTO(Publisher publisher);


}