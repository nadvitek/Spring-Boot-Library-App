package com.example.cv01.service;

import com.example.cv01.entity.Address;
import com.example.cv01.entity.Library;
import com.example.cv01.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    private Library library;

    @BeforeEach
    public void setup(){
        library = Library.builder()
                .id(1L)
                .name("Knihovna Karla Hynka Máchy")
                .address(Mockito.mock(Address.class))
                .books(new ArrayList<>())
                .build();
    }

    @DisplayName("JUnit test for finding Library by id method")
    @Test
    public void saveLibrary_givenLibrarySaved_returnsLibrary(){
        //ARRANGE
        given(libraryRepository.save(library)).willReturn(library);

        //ACT
        Library savedLibrary = libraryService.saveLibrary(library);

        //ASSERT
        assertThat(savedLibrary).isNotNull();
    }

    @DisplayName("JUnit test for saveLibrary method saving null throws Exception")
    @Test
    public void saveLibrary_savingNullLibrary_throwsException(){
        //ARRANGE

        //ACT
        assertThrows(NullPointerException.class, () -> libraryService.saveLibrary(null));

        //ASSERT
        verify(libraryRepository, never()).save(any(Library.class));
    }

    @DisplayName("JUnit test for getAllLibraries method")
    @Test
    public void getAllLibraries_haveListOfTwo_getsListOfTwo(){
        //ARRANGE
        Library library1 = Library.builder()
                .id(2L)
                .name("Knihovna T. G. Masaryka")
                .address(Mockito.mock(Address.class))
                .books(new ArrayList<>())
                .build();

        given(libraryRepository.findAll()).willReturn(List.of(library, library1));

        //ACT
        List<Library> libraryList = libraryService.getAllLibraries();

        //ASSERT
        assertThat(libraryList).isNotNull();
        assertThat(libraryList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findById_whenGetLibraryById_thenReturnLibraryObject(){
        //ARRANGE
        given(libraryRepository.findById(1L)).willReturn(Optional.of(library));

        //ACT
        Library savedLibrary = libraryService.findBy(library.getId());

        //ASSERT
        assertThat(savedLibrary).isNotNull();
    }

    @DisplayName("JUnit test for updateLibrary method")
    @Test
    public void updateLibrary_changesAttributes_returnsUpdatedLibrary(){
        //ARRANGE
        given(libraryRepository.save(library)).willReturn(library);
        library.setName("Národní technická knihovna");

        //ACT
        Library updatedLibrary = libraryService.saveLibrary(library);

        //ASSERT
        assertThat(updatedLibrary.getName()).isEqualTo("Národní technická knihovna");
    }

    @DisplayName("JUnit test for deleteLibrary method")
    @Test
    public void givenLibraryId_whenDeleteLibrary_thenNothing(){
        //ARRANGE
        long libraryId = 1L;

        willDoNothing().given(libraryRepository).deleteById(libraryId);

        //ACT
        libraryService.deleteLibrary(libraryId);

        //ASSERT
        verify(libraryRepository, times(1)).deleteById(libraryId);
    }
}
