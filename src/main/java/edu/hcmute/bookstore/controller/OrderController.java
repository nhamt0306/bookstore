package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.model.OrderEntity;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.TransactionEntity;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.impl.CartProductServiceImpl;
import edu.hcmute.bookstore.service.impl.OrderDetailServiceImpl;
import edu.hcmute.bookstore.service.impl.OrderServiceImpl;
import edu.hcmute.bookstore.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    CartProductServiceImpl cartProductService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @PostMapping("/user/order/create")
    public Object createOrder(@RequestBody List<Object> req) throws ParseException {
        // get request data
        List<Map<String, String>> orderDetailsEntityListReq = (List<Map<String, String>>) req.get(0);
        Map<String, String> orderInformation = (Map<String, String>) req.get(1);
        // create variable for usage
        OrderEntity orderEntity;
        // get user information
        UserEntity user = userDetailService.getCurrentUser();
        long totalcost = Long.valueOf(0);
        // analyze order Product data
//        List<TransactionEntity> orderDetailsEntityList = orderDetailsEntityListReq.stream().map(orderEntityReq -> {
//            TransactionEntity orderDetailsEntity = new TransactionEntity(Long.parseLong(orderEntityReq.get("unit_price")), Long.parseLong(orderEntityReq.get("quantity")), productService.findProductById(Long.parseLong(orderEntityReq.get("product_id"))));
//            totalcost.set(totalcost.get() + (Long.parseLong(orderEntityReq.get("unit_price")) * Long.parseLong(orderEntityReq.get("quantity"))));
//            return orderDetailsEntity;
//        }).collect(Collectors.toList());
        List<TransactionEntity> transactionEntities = new ArrayList<TransactionEntity>();
        for (Map<String, String> transaction : orderDetailsEntityListReq)
        {
            long unitPrice = Long.parseLong(transaction.get("unit_price"));
            System.out.println(unitPrice);
            long quantity = Long.parseLong(transaction.get("quantity"));
            System.out.println(quantity);
            ProductEntity productEntity = productService.findProductById(Long.parseLong(transaction.get("product_id")));
            TransactionEntity transactionEntity = new TransactionEntity(unitPrice, quantity, productEntity);
            totalcost += unitPrice*quantity;
            transactionEntities.add(transactionEntity);
        }
        // map value for orderEntity and check if user want to add new address
        if (orderInformation.get("address") != null) {
            orderEntity = new OrderEntity(totalcost, orderInformation.get("note") == null ? "" : orderInformation.get("note"), Long.parseLong(orderInformation.get("shipping_fee") == null ? "25000" : orderInformation.get("shipping_fee")), orderInformation.get("payment") == null ? "COD" : orderInformation.get("payment"), "PENDING" , user.getUserAddress(), user.getUserPhone());
        } else
        {
            orderEntity = new OrderEntity(totalcost, orderInformation.get("note") == null ? "" : orderInformation.get("note"), Long.parseLong(orderInformation.get("shipping_fee") == null ? "25000" : orderInformation.get("shipping_fee")), orderInformation.get("payment") == null ? "COD" : orderInformation.get("payment"), "PENDING" , orderInformation.get("address") == null ? user.getUserAddress() : orderInformation.get("address"), user.getUserPhone());
        }
        orderEntity.setUserEntities(user);
        System.out.println(orderEntity);
        // map order and order-details
        orderEntity.setOrderDetailsEntities(transactionEntities);
        orderEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));
        orderEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        transactionEntities.forEach(i -> i.setOrderEntity(orderEntity));
        // insert order and details to DB
        orderService.addNewOrder(orderEntity);
        // delete cartProduct from DB
        for (TransactionEntity transactionEntity: transactionEntities)
        {
            transactionEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));
            transactionEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
            orderDetailService.save(transactionEntity);
            cartProductService.deleteProductInCart(user.getId(), transactionEntity.getProductEntity().getId());
        }
        return "create order success";
    }
}
