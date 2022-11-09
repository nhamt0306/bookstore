package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.mapper.ProductMapper;
import edu.hcmute.bookstore.mapper.TransactionMapper;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.TransactionEntity;
import edu.hcmute.bookstore.service.impl.OrderDetailServiceImpl;
import edu.hcmute.bookstore.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderDetailController {
    @Autowired
    OrderDetailServiceImpl orderDetailService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/user/orderdetail/{id}")
    public ResponseEntity<?> getAllByOrderId(@PathVariable long id)
    {
        List<TransactionMapper> transactionMappers = new ArrayList<>();
        for(TransactionEntity transactionEntity : orderDetailService.getAllByOrderId(id))
        {
            TransactionMapper transactionMapper = new TransactionMapper(transactionEntity.getId(),transactionEntity.getTranStatus(), transactionEntity.getTranUnitPrice(), transactionEntity.getTranQuantity(), transactionEntity.getProductEntity().getId(), transactionEntity.getProductEntity().getProImage(), transactionEntity.getProductEntity().getProName(), transactionEntity.getProductEntity().getAuthorEntity().getAutName());
            transactionMappers.add(transactionMapper);
        }
        return ResponseEntity.ok(transactionMappers);
    }
}
