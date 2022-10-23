package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProNameContaining(String name);

}
