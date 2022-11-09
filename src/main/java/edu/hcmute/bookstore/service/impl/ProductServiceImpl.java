package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.repository.ProductRepository;
import edu.hcmute.bookstore.service.CloudinaryService;
import edu.hcmute.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CloudinaryService cloudinaryService;

    @Override
    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<ProductEntity> findProductByName(String name) {
        return productRepository.findByProNameContaining(name);
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        productEntity.setProStatus(LocalVariable.disableStatus);
        productEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        productEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> getPagingProduct(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductEntity> page = productRepository.findAll(pageable);
        return page.toList();
    }

    @Override
    public ProductEntity uploadImage(long id, MultipartFile image) {
        ProductEntity productEntity = productRepository.findById(id).get();
        String imageUrl = cloudinaryService.uploadFile(image,String.valueOf(id),
                "BookStore"+ "/" + "Product");
        if(!imageUrl.equals("-1")) {
            productEntity.setProImage(imageUrl);
        }
        else if(productEntity.getProImage().equals("") || productEntity.getProImage().equals("-1"))
            productEntity.setProImage("");

        return productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> findAllByCatId(Long id) {
        return productRepository.findAllByCategoryEntityId(id);
    }
}
