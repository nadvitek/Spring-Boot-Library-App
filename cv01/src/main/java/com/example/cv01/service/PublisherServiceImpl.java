package com.example.cv01.service;

import co.infinum.retromock.meta.Mock;
import co.infinum.retromock.meta.MockResponse;
import com.example.cv01.aspect.ResourceBodyFactory;
import com.example.cv01.entity.Publisher;
import com.example.cv01.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final static Logger LOG = LoggerFactory.getLogger(PublisherServiceImpl.class);

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
        LOG.info("PublisherServiceImpl constructed");
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "publishers.json")
    @CacheEvict(value = "publishers", allEntries = true)
    public Publisher findBy(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for getting a Publisher");
            throw new IllegalArgumentException("No id for Publisher found.");
        }

        return publisherRepository.findById(id).orElseThrow(() -> new NullPointerException("No publisher found for this id"));
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "publishers.json")
    @CachePut("publishers")
    public Publisher savePublisher(Publisher publisher) {
        if (publisher == null) {
            LOG.error("There was no publisher provided for saving a Publisher");
            throw new NullPointerException("No Publisher");
        }

        return publisherRepository.save(publisher);
    }

    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "publishers.json")
    @CacheEvict(value = "publishers", allEntries = true)
    public List<Publisher> getAllPublishers() {
        return (List<Publisher>) publisherRepository.findAll();
    }


    @Mock
    @MockResponse(bodyFactory = ResourceBodyFactory.class, body = "publishers.json")
    public void deletePublisher(Long id) {
        if (id == null) {
            LOG.error("There was no id provided for deleting a Publisher");
            throw new IllegalArgumentException("No id for Publisher found.");
        }

        publisherRepository.deleteById(id);
    }
}
