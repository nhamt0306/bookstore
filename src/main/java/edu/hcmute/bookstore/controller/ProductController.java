package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.ProductDTO;
import edu.hcmute.bookstore.mapper.ProductMapper;
import edu.hcmute.bookstore.model.AuthorEntity;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.PublisherEntity;
import edu.hcmute.bookstore.service.impl.AuthorServiceImpl;
import edu.hcmute.bookstore.service.impl.CategoryServiceImpl;
import edu.hcmute.bookstore.service.impl.ProductServiceImpl;
import edu.hcmute.bookstore.service.impl.PublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    PublisherServiceImpl publisherService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    AuthorServiceImpl authorService;
    @GetMapping("/product/getAll/{pageNo}/{pageSize}")
    public ResponseEntity<?> getAllProduct(@PathVariable Integer pageNo, @PathVariable Integer pageSize)
    {
        List<ProductMapper> productMapperList = new ArrayList<>();
        for(ProductEntity productEntity : productService.getPagingProduct(pageNo, pageSize))
        {
            ProductMapper productMapper = new ProductMapper(productEntity.getId(),productEntity.getProName(), productEntity.getProDescription(), productEntity.getProContent(), productEntity.getProPrice(), productEntity.getProQuantity(), productEntity.getProSale(), productEntity.getProImage(), productEntity.getCategoryEntity(), productEntity.getAuthorEntity(), productEntity.getPublisherEntity());
            productMapperList.add(productMapper);
        }
        return ResponseEntity.ok(productMapperList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id){
        try {
            ProductEntity productEntity = productService.findProductById(id);
            ProductMapper productMapper = new ProductMapper(productEntity.getId(),productEntity.getProName(), productEntity.getProDescription(), productEntity.getProContent(), productEntity.getProPrice(), productEntity.getProQuantity(), productEntity.getProSale(), productEntity.getProImage(), productEntity.getCategoryEntity(), productEntity.getAuthorEntity(), productEntity.getPublisherEntity());
            return ResponseEntity.ok(productMapper);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/category/{id}")
    public ResponseEntity<?> getProductByCatId(@PathVariable long id){
        List<ProductMapper> productMapperList = new ArrayList<>();
        for(ProductEntity productEntity : productService.findAllByCatId(id))
        {
            ProductMapper productMapper = new ProductMapper(productEntity.getId(),productEntity.getProName(), productEntity.getProDescription(), productEntity.getProContent(), productEntity.getProPrice(), productEntity.getProQuantity(), productEntity.getProSale(), productEntity.getProImage(), productEntity.getCategoryEntity(), productEntity.getAuthorEntity(), productEntity.getPublisherEntity());
            productMapperList.add(productMapper);
        }
        return ResponseEntity.ok(productMapperList);
    }

    @PostMapping("/admin/product/create")
    public Object createProduct(@RequestBody ProductDTO productDTO) throws ParseException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProName(productDTO.getProName());
        productEntity.setProContent(productDTO.getProContent());
        productEntity.setProDescription(productDTO.getProDescription());
        productEntity.setProPrice(productDTO.getProPrice());
        productEntity.setProQuantity(productDTO.getProQuantity());
        productEntity.setProSale(productDTO.getProSale());
        productEntity.setProImage(productDTO.getProImage());
        productEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        productEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));

        CategoryEntity categoryEntity = categoryService.findCategoryById(productDTO.getCategoryId());
        PublisherEntity publisher = publisherService.findPublisherById(productDTO.getPublisherId());
        AuthorEntity authorEntity = authorService.findAuthorById(productDTO.getAuthorId());

        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setAuthorEntity(authorEntity);
        productEntity.setPublisherEntity(publisher);

        return productService.save(productEntity);
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable long id)
    {
        productService.delete(id);
        return ResponseEntity.ok(LocalVariable.messageDeleteCatSuccess);
    }
    @GetMapping("/product/getAll")
    public ResponseEntity<?> deleteProductById()
    {
        List<ProductMapper> productMapperList = new ArrayList<>();
        for(ProductEntity productEntity : productService.getAllProduct())
        {
            ProductMapper productMapper = new ProductMapper(productEntity.getId(),productEntity.getProName(), productEntity.getProDescription(), productEntity.getProContent(), productEntity.getProPrice(), productEntity.getProQuantity(), productEntity.getProSale(), productEntity.getProImage(), productEntity.getCategoryEntity(), productEntity.getAuthorEntity(), productEntity.getPublisherEntity());
            productMapperList.add(productMapper);
        }
        return ResponseEntity.ok(productMapperList);
    }
}
