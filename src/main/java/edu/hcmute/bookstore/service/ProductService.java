package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProduct();
    ProductEntity save(ProductEntity productEntity);
    ProductEntity findProductById(Long id);
    List<ProductEntity> findProductByName(String name);
    void delete(Long id);
    List<ProductEntity> getPagingProduct(Integer pageNo, Integer pageSize);
    ProductEntity uploadImage(long id, MultipartFile image);
}
