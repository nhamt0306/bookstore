package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
