package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.model.PublisherEntity;
import edu.hcmute.bookstore.repository.PublisherRepository;
import edu.hcmute.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public List<PublisherEntity> getAllPublisher() {
        return publisherRepository.findAll();
    }

    @Override
    public PublisherEntity save(PublisherEntity publisherEntity) {
        return publisherRepository.save(publisherEntity);
    }

    @Override
    public PublisherEntity findPublisherById(Long id) {
        return publisherRepository.findById(id).get();
    }

    @Override
    public List<PublisherEntity> findPublisherByName(String name) {
        return publisherRepository.findByPubNameContaining(name);
    }

    @Override
    public void deletePublisherById(Long id) {
        PublisherEntity publisherEntity = publisherRepository.findById(id).get();
        publisherEntity.setPubStatus(LocalVariable.disableStatus);
        publisherEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        publisherRepository.save(publisherEntity);
    }
}
