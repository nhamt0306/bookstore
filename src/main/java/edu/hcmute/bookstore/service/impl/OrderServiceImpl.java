package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.model.OrderEntity;
import edu.hcmute.bookstore.repository.OrderRepository;
import edu.hcmute.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<OrderEntity> getAllPaging(int page) {
        return orderRepository.findAll(PageRequest.of(page, LocalVariable.OrderPagingLimit));
    }

    @Override
    public Page<OrderEntity> getAllByUserId(Long UserId, int page) {
        return orderRepository.findAllByUserEntitiesId(UserId,PageRequest.of(page,LocalVariable.OrderPagingLimit,
                Sort.by("id").descending()));
    }

    @Override
    public List<OrderEntity> getAllPendingOrderByUserId(Long UserId, String Status) {
        return orderRepository.getAllByUserEntitiesIdAndOrdStatus(UserId, Status);
    }

    @Override
    public List<OrderEntity> getAllHistoryOrderByUserId(Long UserId, String Status) {
        return orderRepository.getAllByUserEntitiesIdAndOrdStatusNot(UserId, Status);
    }


    @Override
    public OrderEntity findOrderById(Long OrderId) {
        return orderRepository.findById(OrderId).get();
    }

    @Override
    public void addNewOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderEntity> getAllByUserId(Long userid) {
        return orderRepository.getAllByUserEntitiesId(userid);
    }

    @Override
    public List<OrderEntity> getAllOrder() {
        return orderRepository.findAll();
    }
}
