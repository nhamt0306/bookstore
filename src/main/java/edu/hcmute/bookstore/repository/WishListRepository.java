package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishListEntity, Long> {
}
