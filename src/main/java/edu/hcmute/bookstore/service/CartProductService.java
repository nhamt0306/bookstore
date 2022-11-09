package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.CartProductEntity;

import java.util.Collection;
import java.util.List;

public interface CartProductService {
    CartProductEntity save(CartProductEntity cartProductEntity);
    List<CartProductEntity> getAllProductByCartId(Long cartId);
    void deleteProductInCart(Long cartId, Long productId);
    Boolean existsByProduct(Long productId, Long cartId);
    CartProductEntity increaseQuantity(Long productId, Long cartId);
    CartProductEntity decreaseQuantity(Long productId, Long cartId);
}
