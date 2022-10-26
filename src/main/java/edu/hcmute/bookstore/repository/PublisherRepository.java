package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    List<PublisherEntity> findByPubNameContaining(String name);
}
