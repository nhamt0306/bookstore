package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.PublisherEntity;

import java.util.List;

public interface PublisherService {
    List<PublisherEntity> getAllPublisher();
    PublisherEntity save(PublisherEntity publisherEntity);
    PublisherEntity findPublisherById(Long id);
    List<PublisherEntity> findPublisherByName(String name);
    void deletePublisherById(Long id);

}
