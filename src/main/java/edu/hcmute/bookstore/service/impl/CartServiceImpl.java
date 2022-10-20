package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.CartEntity;
import edu.hcmute.bookstore.repository.CartRepository;
import edu.hcmute.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Override
    public CartEntity save(Long id) {
        return cartRepository.save(new CartEntity(id));
    }
}
