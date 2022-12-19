package com.example.cv01.service;

import com.example.cv01.entity.Library;

import java.util.List;

public interface LibraryService {
    Library findBy(Long id);
    List<Library> getAllLibraries();
    Library saveLibrary(Library library);
    void deleteLibrary(Long id);
}
