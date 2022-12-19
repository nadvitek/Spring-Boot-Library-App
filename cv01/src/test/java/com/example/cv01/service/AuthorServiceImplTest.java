package com.example.cv01.service;

import com.example.cv01.entity.Author;
import com.example.cv01.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;

    @BeforeEach
    public void setup(){
        author = Author.builder()
                .id(1L)
                .name("Josef")
                .surname("Čapek")
                .mail("j.capek@gmail.com")
                .build();
    }

    @DisplayName("JUnit test for finding Author by id method")
    @Test
    public void saveAuthor_givenAuthorSaved_returnsAuthor(){
        //ARRANGE
        given(authorRepository.save(author)).willReturn(author);

        //ACT
        Author savedAuthor = authorService.saveAuthor(author);

        //ASSERT
        assertThat(savedAuthor).isNotNull();
    }

    @DisplayName("JUnit test for saveAuthor method saving null throws Exception")
    @Test
    public void saveAuthor_savingNullAuthor_throwsException(){
        //ARRANGE

        //ACT
        assertThrows(NullPointerException.class, () -> authorService.saveAuthor(null));

        //ASSERT
        verify(authorRepository, never()).save(any(Author.class));
    }

    @DisplayName("JUnit test for getAllAuthores method")
    @Test
    public void getAllAuthores_haveListOfTwo_getsListOfTwo(){
        //ARRANGE
        Author author1 = Author.builder()
                .id(2L)
                .name("Karel")
                .surname("Čapek")
                .mail("k.capek@gmail.com")
                .build();

        given(authorRepository.findAll()).willReturn(List.of(author, author1));

        //ACT
        List<Author> authorList = authorService.getAllAuthors();

        //ASSERT
        assertThat(authorList).isNotNull();
        assertThat(authorList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findById_whenGetAuthorById_thenReturnAuthorObject(){
        //ARRANGE
        given(authorRepository.findById(1L)).willReturn(Optional.of(author));

        //ACT
        Author savedAuthor = authorService.findBy(author.getId());

        //ASSERT
        assertThat(savedAuthor).isNotNull();
    }

    @DisplayName("JUnit test for updateAuthor method")
    @Test
    public void updateAuthor_changesAttributes_returnsUpdatedAuthor(){
        //ARRANGE
        given(authorRepository.save(author)).willReturn(author);
        author.setSurname("Lada");
        author.setMail("j.lada@seznam.cz");

        //ACT
        Author updatedAuthor = authorService.saveAuthor(author);

        //ASSERT
        assertThat(updatedAuthor.getSurname()).isEqualTo("Lada");
        assertThat(updatedAuthor.getMail()).isEqualTo("j.lada@seznam.cz");
    }

    @DisplayName("JUnit test for deleteAuthor method")
    @Test
    public void givenAuthorId_whenDeleteAuthor_thenNothing(){
        //ARRANGE
        long authorId = 1L;

        willDoNothing().given(authorRepository).deleteById(authorId);

        //ACT
        authorService.deleteAuthor(authorId);

        //ASSERT
        verify(authorRepository, times(1)).deleteById(authorId);
    }
}
