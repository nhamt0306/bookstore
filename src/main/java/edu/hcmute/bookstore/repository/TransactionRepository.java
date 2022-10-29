package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByOrderEntityId(Long orderId);
}
