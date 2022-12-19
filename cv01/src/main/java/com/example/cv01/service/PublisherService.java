package com.example.cv01.service;

import com.example.cv01.entity.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher findBy(Long id);
    List<Publisher> getAllPublishers();
    Publisher savePublisher(Publisher publisher);
    void deletePublisher(Long id);
}
