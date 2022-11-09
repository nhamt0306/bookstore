package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.CartProductEntity;
import edu.hcmute.bookstore.repository.CartProductRepository;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
@Service
public class CartProductServiceImpl implements CartProductService {
    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    UserDetailService userDetailService;

    @Override
    public CartProductEntity save(CartProductEntity cartProductEntity) {
        if (existsByProduct(cartProductEntity.getProductEntity().getId(), cartProductEntity.getCartEntity().getId()))
        {
            CartProductEntity cartProduct = cartProductRepository.findByCartEntityIdAndProductEntityId(cartProductEntity.getCartEntity().getId(), cartProductEntity.getProductEntity().getId());
            cartProduct.setQuantity(cartProduct.getQuantity()+cartProductEntity.getQuantity());
            return cartProductRepository.save(cartProduct);
        }
        return cartProductRepository.save(cartProductEntity);
    }

    @Override
    public List<CartProductEntity> getAllProductByCartId(Long cartId) {
        return cartProductRepository.getAllProductInCart(cartId);
    }

    @Override
    @Transactional
    public void deleteProductInCart(Long cartId, Long productId) {
        cartProductRepository.deleteByCartEntityIdAndProductEntityId(cartId, productId);
    }

    @Override
    public Boolean existsByProduct(Long productId, Long cartId) {
        return cartProductRepository.existsByProductEntityIdAndCartEntityId(productId, cartId);
    }

    @Override
    public CartProductEntity increaseQuantity(Long productId, Long cartId) {
        CartProductEntity cartProduct = cartProductRepository.findByCartEntityIdAndProductEntityId(cartId, productId);
        cartProduct.setQuantity(cartProduct.getQuantity() + 1);
        return cartProductRepository.save(cartProduct);
    }

    @Override
    public CartProductEntity decreaseQuantity(Long productId, Long cartId) {
        CartProductEntity cartProduct = cartProductRepository.findByCartEntityIdAndProductEntityId(cartId, productId);
        if (cartProduct.getQuantity() == 1)
        {
            cartProductRepository.deleteByCartEntityIdAndProductEntityId(cartId, productId);
        }
        cartProduct.setQuantity(cartProduct.getQuantity() - 1);
        return cartProductRepository.save(cartProduct);
    }
}
