package com.example.cv01.service;

import com.example.cv01.entity.Address;
import com.example.cv01.entity.Publisher;
import com.example.cv01.repository.PublisherRepository;
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
public class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    private Publisher publisher;

    @BeforeEach
    public void setup(){
        publisher = Publisher.builder()
                .id(1L)
                .name("LEDA")
                .address(Mockito.mock(Address.class))
                .books(new ArrayList<>())
                .authors(new ArrayList())
                .build();
    }

    @DisplayName("JUnit test for finding Publisher by id method")
    @Test
    public void savePublisher_givenPublisherSaved_returnsPublisher(){
        //ARRANGE
        given(publisherRepository.save(publisher)).willReturn(publisher);

        //ACT
        Publisher savedPublisher = publisherService.savePublisher(publisher);

        //ASSERT
        assertThat(savedPublisher).isNotNull();
    }

    @DisplayName("JUnit test for savePublisher method saving null throws Exception")
    @Test
    public void savePublisher_savingNullPublisher_throwsException(){
        //ARRANGE

        //ACT
        assertThrows(NullPointerException.class, () -> publisherService.savePublisher(null));

        //ASSERT
        verify(publisherRepository, never()).save(any(Publisher.class));
    }

    @DisplayName("JUnit test for getAllPublishers method")
    @Test
    public void getAllPublishers_haveListOfTwo_getsListOfTwo(){
        //ARRANGE
        Publisher publisher1 = Publisher.builder()
                .id(2L)
                .name("GRADA")
                .address(Mockito.mock(Address.class))
                .books(new ArrayList<>())
                .authors(new ArrayList())
                .build();

        given(publisherRepository.findAll()).willReturn(List.of(publisher, publisher1));

        //ACT
        List<Publisher> publisherList = publisherService.getAllPublishers();

        //ASSERT
        assertThat(publisherList).isNotNull();
        assertThat(publisherList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findById_whenGetPublisherById_thenReturnPublisherObject(){
        //ARRANGE
        given(publisherRepository.findById(1L)).willReturn(Optional.of(publisher));

        //ACT
        Publisher savedPublisher = publisherService.findBy(publisher.getId());

        //ASSERT
        assertThat(savedPublisher).isNotNull();
    }

    @DisplayName("JUnit test for updatePublisher method")
    @Test
    public void updatePublisher_changesAttributes_returnsUpdatedPublisher(){
        //ARRANGE
        given(publisherRepository.save(publisher)).willReturn(publisher);
        publisher.setName("Svojtka");

        //ACT
        Publisher updatedPublisher = publisherService.savePublisher(publisher);

        //ASSERT
        assertThat(updatedPublisher.getName()).isEqualTo("Svojtka");
    }

    @DisplayName("JUnit test for deletePublisher method")
    @Test
    public void givenPublisherId_whenDeletePublisher_thenNothing(){
        //ARRANGE
        long publisherId = 1L;

        willDoNothing().given(publisherRepository).deleteById(publisherId);

        //ACT
        publisherService.deletePublisher(publisherId);

        //ASSERT
        verify(publisherRepository, times(1)).deleteById(publisherId);
    }
}
