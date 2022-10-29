package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.TransactionEntity;

public interface OrderDetailService {
    TransactionEntity save(TransactionEntity transactionEntity);
}
