package com.example.cv01.service;

import com.example.cv01.entity.Book;
import com.example.cv01.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    public void setup(){
        book = Book.builder()
                .id(1L)
                .isbn("XXX")
                .name("LOTR")
                .genre("fantasy")
                .publishDate(new Date())
                .build();
    }

    @DisplayName("JUnit test for finding Book by id method")
    @Test
    public void saveBook_givenBookSaved_returnsBook(){
        //ARRANGE
        given(bookRepository.save(book)).willReturn(book);

        //ACT
        Book savedBook = bookService.saveBook(book);

        //ASSERT
        assertThat(savedBook).isNotNull();
    }

    @DisplayName("JUnit test for saveBook method saving null throws Exception")
    @Test
    public void saveBook_savingNullBook_throwsException(){
        //ARRANGE

        //ACT
        assertThrows(NullPointerException.class, () -> bookService.saveBook(null));

        //ASSERT
        verify(bookRepository, never()).save(any(Book.class));
    }

    @DisplayName("JUnit test for getAllBooks method")
    @Test
    public void getAllBooks_haveListOfTwo_getsListOfTwo(){
        //ARRANGE
        Book book1 = Book.builder()
                .id(2L)
                .isbn("YYY")
                .name("Terminator")
                .genre("sci-fi")
                .publishDate(new Date())
                .build();

        given(bookRepository.findAll()).willReturn(List.of(book, book1));

        //ACT
        List<Book> bookList = bookService.getAllBooks();

        //ASSERT
        assertThat(bookList).isNotNull();
        assertThat(bookList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findById_whenGetBookById_thenReturnBookObject(){
        //ARRANGE
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));

        //ACT
        Book savedBook = bookService.findBy(book.getId());

        //ASSERT
        assertThat(savedBook).isNotNull();
    }

    @DisplayName("JUnit test for updateBook method")
    @Test
    public void updateBook_changesAttributes_returnsUpdatedBook(){
        //ARRANGE
        given(bookRepository.save(book)).willReturn(book);
        book.setName("Hobbit");
        book.setIsbn("ZZZ");

        //ACT
        Book updatedBook = bookService.saveBook(book);

        //ASSERT
        assertThat(updatedBook.getName()).isEqualTo("Hobbit");
        assertThat(updatedBook.getIsbn()).isEqualTo("ZZZ");
    }

    @DisplayName("JUnit test for deleteBook method")
    @Test
    public void givenBookId_whenDeleteBook_thenNothing(){
        //ARRANGE
        long bookId = 1L;

        willDoNothing().given(bookRepository).deleteById(bookId);

        //ACT
        bookService.deleteBook(bookId);

        //ASSERT
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
