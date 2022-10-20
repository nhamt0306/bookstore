package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
