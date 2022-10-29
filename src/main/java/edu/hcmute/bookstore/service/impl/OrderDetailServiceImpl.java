package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.TransactionEntity;
import edu.hcmute.bookstore.repository.TransactionRepository;
import edu.hcmute.bookstore.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public TransactionEntity save(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getAllByOrderId(Long orderId) {
        return transactionRepository.findAllByOrderEntityId(orderId);
    }
}
