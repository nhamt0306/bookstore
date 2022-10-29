package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.TransactionEntity;

import java.util.List;

public interface OrderDetailService {
    TransactionEntity save(TransactionEntity transactionEntity);
    List<TransactionEntity> getAllByOrderId(Long orderId);
}
