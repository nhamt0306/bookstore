package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    List<PublisherEntity> findByPubNameContaining(String name);
}
