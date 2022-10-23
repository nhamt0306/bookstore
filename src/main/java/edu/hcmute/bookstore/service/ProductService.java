package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProduct();
    ProductEntity save(ProductEntity productEntity);
    ProductEntity findProductById(Long id);
    List<ProductEntity> findProductByName(String name);
    void delete(Long id);
}
