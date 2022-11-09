package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProNameContaining(String name);
    List<ProductEntity> findAllByCategoryEntityId(Long categoryId);
}
