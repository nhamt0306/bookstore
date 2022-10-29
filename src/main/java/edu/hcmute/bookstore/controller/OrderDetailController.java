package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.service.impl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {
    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @GetMapping("/user/orderdetail/{id}")
    public ResponseEntity<?> getAllByOrderId(@PathVariable long id)
    {
        return ResponseEntity.ok(orderDetailService.getAllByOrderId(id));
    }
}
