package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.mapper.OrderMapper;
import edu.hcmute.bookstore.mapper.TransactionMapper;
import edu.hcmute.bookstore.model.*;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    @Autowired
    AddressServiceImpl addressService;

    @GetMapping("/admin/orders/getOrderByUserId/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable long id){
        try {
            List<OrderEntity> orderEntityList = orderService.getAllByUserId(id);
            List<OrderMapper> orderMappers = new ArrayList<>();
            for (OrderEntity orderEntity : orderEntityList)
            {
                OrderMapper orderMapper = new OrderMapper(orderEntity.getId(), orderEntity.getOrdTotalPrice(), orderEntity.getOrdNote(), orderEntity.getOrdShippingFee(), orderEntity.getOrdPayment(), orderEntity.getOrdStatus(), orderEntity.getOrdAddress(), orderEntity.getOrdPhone(), orderEntity.getCreate_at());
                orderMapper.setOrdUsername(orderEntity.getOrdFullName());
                // get transaction of ~ order
                List<TransactionMapper> transactionMappers = new ArrayList<>();
                for(TransactionEntity transactionEntity : orderDetailService.getAllByOrderId(orderEntity.getId()))
                {
                    TransactionMapper transactionMapper = new TransactionMapper(transactionEntity.getId(),transactionEntity.getTranStatus(), transactionEntity.getTranUnitPrice(), transactionEntity.getTranQuantity(), transactionEntity.getProductEntity().getId(), transactionEntity.getProductEntity().getProImage(), transactionEntity.getProductEntity().getProName(), transactionEntity.getProductEntity().getAuthorEntity().getAutName());
                    transactionMappers.add(transactionMapper);
                }
                orderMapper.setTransactionMapper(transactionMappers);
                orderMappers.add(orderMapper);
            }

            return ResponseEntity.ok(orderMappers);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/orders/getAll")
    public ResponseEntity<?> getAllOrder(){
        try {
            List<OrderEntity> orderEntityList = orderService.getAllOrder();
            List<OrderMapper> orderMappers = new ArrayList<>();
            for (OrderEntity orderEntity : orderEntityList)
            {
                OrderMapper orderMapper = new OrderMapper(orderEntity.getId(), orderEntity.getOrdTotalPrice(), orderEntity.getOrdNote(), orderEntity.getOrdShippingFee(), orderEntity.getOrdPayment(), orderEntity.getOrdStatus(), orderEntity.getOrdAddress(), orderEntity.getOrdPhone(), orderEntity.getCreate_at());
                orderMapper.setOrdUsername(orderEntity.getOrdFullName());
                // get transaction of ~ order
                List<TransactionMapper> transactionMappers = new ArrayList<>();
                for(TransactionEntity transactionEntity : orderDetailService.getAllByOrderId(orderEntity.getId()))
                {
                    TransactionMapper transactionMapper = new TransactionMapper(transactionEntity.getId(),transactionEntity.getTranStatus(), transactionEntity.getTranUnitPrice(), transactionEntity.getTranQuantity(), transactionEntity.getProductEntity().getId(), transactionEntity.getProductEntity().getProImage(), transactionEntity.getProductEntity().getProName(), transactionEntity.getProductEntity().getAuthorEntity().getAutName());
                    transactionMappers.add(transactionMapper);
                }
                orderMapper.setTransactionMapper(transactionMappers);
                orderMappers.add(orderMapper);
            }

            return ResponseEntity.ok(orderMappers);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Cannot find any order!", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/order/getAll")
    public ResponseEntity<?> getOrderByUserLogin(){
        try {
            List<OrderEntity> orderEntityList = orderService.getAllByUserId(userDetailService.getCurrentUser().getId());
            List<OrderMapper> orderMappers = new ArrayList<>();
            for (OrderEntity orderEntity : orderEntityList)
            {
                OrderMapper orderMapper = new OrderMapper(orderEntity.getId(), orderEntity.getOrdTotalPrice(), orderEntity.getOrdNote(), orderEntity.getOrdShippingFee(), orderEntity.getOrdPayment(), orderEntity.getOrdStatus(), orderEntity.getOrdAddress(), orderEntity.getOrdPhone(), orderEntity.getCreate_at());
                orderMapper.setOrdUsername(orderEntity.getOrdFullName());
                // get transaction of ~ order
                List<TransactionMapper> transactionMappers = new ArrayList<>();
                for(TransactionEntity transactionEntity : orderDetailService.getAllByOrderId(orderEntity.getId()))
                {
                    TransactionMapper transactionMapper = new TransactionMapper(transactionEntity.getId(),transactionEntity.getTranStatus(), transactionEntity.getTranUnitPrice(), transactionEntity.getTranQuantity(), transactionEntity.getProductEntity().getId(), transactionEntity.getProductEntity().getProImage(), transactionEntity.getProductEntity().getProName(), transactionEntity.getProductEntity().getAuthorEntity().getAutName());
                    transactionMappers.add(transactionMapper);
                }
                orderMapper.setTransactionMapper(transactionMappers);
                orderMappers.add(orderMapper);
            }

            return ResponseEntity.ok(orderMappers);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat , HttpStatus.NOT_FOUND);
        }
    }

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
            if (quantity > productEntity.getProQuantity())
            {
                return "Dont enought quantity of "+ productEntity.getProName();
            }
//            productEntity.setProSold(productEntity.getProSold() + quantity);
//            productEntity.setProQuantity(productEntity.getProQuantity() - quantity);
            TransactionEntity transactionEntity = new TransactionEntity(unitPrice, quantity, productEntity);
            totalcost += unitPrice*quantity;
//            productService.save(productEntity);
            transactionEntities.add(transactionEntity);
        }
        // map value for orderEntity and check if user want to add new address
        if (orderInformation.get("address") != null) {
            orderEntity = new OrderEntity(totalcost, orderInformation.get("note") == null ? "" : orderInformation.get("note"), Long.parseLong(orderInformation.get("shipping_fee") == null ? "25000" : orderInformation.get("shipping_fee")), orderInformation.get("payment") == null ? "COD" : orderInformation.get("payment"), "PENDING" , user.getUserAddress(), user.getUserPhone());
        } else
        {
            AddressEntity address = addressService.getAddressDefaultOfUser(user.getId());
            orderEntity = new OrderEntity(totalcost, address.getNote(), Long.parseLong(orderInformation.get("shipping_fee") == null ? "25000" : orderInformation.get("shipping_fee")), orderInformation.get("payment") == null ? "COD" : orderInformation.get("payment"), "PENDING" , address.getAddress(), address.getPhoneNumber());
            if (totalcost > Long.valueOf(250000L))
            {
                orderEntity.setOrdShippingFee(Long.valueOf(0L));
            }
            else
            {
                orderEntity.setOrdShippingFee(Long.valueOf(15000));
            }
            orderEntity.setOrdFullName(address.getName());
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

    @PostMapping("/admin/order/deny/{id}")
    public Object cancelOrder(@PathVariable long id) {
        OrderEntity orderEntity = orderService.findOrderById(id);
        if (orderEntity.getOrdStatus().equals("PENDING")) // N???u t??nh tr???ng l?? ??ang ?????i th?? m???i ???????c h???y
        {
            orderEntity.setOrdStatus(LocalVariable.cancelMessage);
            orderEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
            orderService.addNewOrder(orderEntity);
            return "cancel order success";
        }
        return "cancel order fail";
    }

    @PostMapping("/user/order/deny/{id}")
    public Object denyOrderByUser(@PathVariable long id) {
        OrderEntity orderEntity = orderService.findOrderById(id);
        if (orderEntity.getOrdStatus().equals("PENDING")) // N???u t??nh tr???ng l?? ??ang ?????i th?? m???i ???????c h???y
        {
            orderEntity.setOrdStatus(LocalVariable.cancelMessage);
            orderEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
            orderService.addNewOrder(orderEntity);
            return "cancel order success";
        }
        return "cancel order fail";
    }

    @PostMapping("/admin/order/accept/{id}")
    public Object acceptOrder(@PathVariable long id) {
        OrderEntity orderEntity = orderService.findOrderById(id);
        // Only update
        if (orderEntity.getOrdStatus().equals(LocalVariable.deliveringMessage)) // N???u t??nh tr???ng l?? ??ang ?????i th?? m???i ???????c h???y
        {
            orderEntity.setOrdStatus(LocalVariable.doneMessage);
            orderEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
            orderService.addNewOrder(orderEntity);
            return "update status order success";
        }

        //update product quantity

        List<TransactionEntity> transactionEntities = orderDetailService.getAllByOrderId(orderEntity.getId());
        for (TransactionEntity transactionEntity : transactionEntities)
        {
            ProductEntity productEntity = productService.findProductById(transactionEntity.getProductEntity().getId());
            if (productEntity.getProQuantity() < transactionEntity.getTranQuantity())
            {
                return "Enough quantity";
            }
            productEntity.setProSold(productEntity.getProSold() + transactionEntity.getTranQuantity());
            productEntity.setProQuantity(productEntity.getProQuantity() - transactionEntity.getTranQuantity());
            productService.save(productEntity);
        }

        if (orderEntity.getOrdStatus().equals(LocalVariable.pendingMessage)) // N???u t??nh tr???ng l?? ??ang ?????i th?? m???i ???????c h???y
        {
            orderEntity.setOrdStatus(LocalVariable.deliveringMessage);
            orderEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
            orderService.addNewOrder(orderEntity);
            return "update status order with done: success";
        }
        return "update status order fail";
    }
    @GetMapping("/get_deliver_fee")
    public Object getShippingFee(@RequestParam String f, @RequestParam String t, @RequestParam String w) {
        String httpRequest = "Can't get deliver fee, Server Busy";

        try {
            URL url = new URL("http://www.vnpost.vn/vi-vn/tra-cuu-gia-cuoc?from=" + f + "&to=" + t + "&weight=" + w);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String line;
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder resHtml = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                resHtml.append(line);
            }
            bufferedReader.close();
            httpRequest = resHtml.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpRequest;
    }


}
