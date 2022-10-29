package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    @Query(value = "SELECT * FROM carts_products WHERE cart_id = :CartId",nativeQuery = true)
    List<CartProductEntity> getAllProductInCart(@Param("CartId") Long CartId);
    // check product exist in cart?
    Boolean existsByProductEntityIdAndCartEntityId(Long productId, Long cartId);
    CartProductEntity save(CartProductEntity cartProductEntity);
    CartProductEntity findByCartEntityIdAndProductEntityId(Long cartId, Long productId);
    void deleteByCartEntityIdAndProductEntityId(Long cartId, Long productId);
}
