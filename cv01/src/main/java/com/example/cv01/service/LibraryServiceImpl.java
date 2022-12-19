package com.example.cv01.service;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import com.example.cv01.aspect.ResourceBodyFactory;
import com.example.cv01.entity.Library;
import com.example.cv01.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService{

    private final LibraryRepository libraryRepository;
    private final static Logger LOG = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
        LOG.info("LibraryServiceImpl constructed");
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "libraries.json")
    @CacheEvict(value = "libraries", allEntries = true)
    public Library findBy(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for getting a Library");
            throw new IllegalArgumentException("No Id for Library");
        }

        return libraryRepository.findById(id).orElseThrow(() -> new NullPointerException("No library found."));
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "libraries.json")
    @CachePut("libraries")
    public Library saveLibrary(Library library) {
        if (library == null) {
            LOG.error("There was no library provided for saving an Library");
            throw new NullPointerException("No Library");
        }

        return libraryRepository.save(library);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "libraries.json")
    @CacheEvict(value = "libraries", allEntries = true)
    public List<Library> getAllLibraries() {
        return (List<Library>) libraryRepository.findAll();
    }


    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "libraries.json")
    public void deleteLibrary(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for deleting a Library");
            throw new IllegalArgumentException("No id for library found.");
        }

        libraryRepository.deleteById(id);
    }

}
