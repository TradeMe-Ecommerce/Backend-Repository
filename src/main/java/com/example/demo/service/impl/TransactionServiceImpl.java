package com.example.demo.service.impl;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.entity.Transaction;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<TransactionDTO> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .toList();
    }

    @Override
    public TransactionDTO findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(transactionMapper::toDTO).orElse(null);
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO, long buyerId, String date) {
        try {
            OrderDTO order = orderService.findByDateAndUser(date, buyerId);
            transactionDTO.setOrderId(order.getId());
            Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDTO));
            List<Long> ids = order.getTransactionIds();
            if (ids == null) {
                ids = List.of(transaction.getId());
            } else {
                ids.add(transaction.getId());
            }
            order.setTransactionIds(ids);
            orderService.updateOrder(order);
            return transactionMapper.toDTO(transaction);
        } catch (Exception e) {
            if (e.getMessage().contains("Order not found")) {
                OrderDTO n = new OrderDTO();
                n.setDate(date);
                n.setHistoryId(historyRepository.findByUser_Id(buyerId).orElseThrow(
                        () -> new RuntimeException("History not found for user ID: " + buyerId)
                ).getId());
                n = orderService.createOrder(n);
                transactionDTO.setOrderId(n.getId());
                Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDTO));
                n.setTransactionIds(List.of(transactionDTO.getId()));
                orderService.updateOrder(n);
                return transactionMapper.toDTO(transaction);
            } else {
                throw new RuntimeException("An error occurred while fetching the order: " + e.getMessage());
            }
        }
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(transactionDTO.getId()).orElseThrow(
                () -> new RuntimeException("Transaction not found with ID: " + transactionDTO.getId()));

        transaction.setStatus(transactionDTO.getStatus());
        transaction.setPrice(transactionDTO.getPrice());
        transaction.setPaymentMethod(transactionDTO.getPaymentMethod());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setProduct(productRepository.findById(transactionDTO.getProductId()).orElseThrow(
                () -> new RuntimeException("Product not found with ID: " + transaction.getProduct().getId())
        ));
        return transactionMapper.toDTO(transactionRepository.save(transaction));
    }

    @Override
    public void deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        }
    }

    @Override
    public List<TransactionDTO> findByUserId(Long id) {
        List<Transaction> transactions = transactionRepository.findByUser_Id(id);
        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions found for user ID: " + id);
        }
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .toList();
    }
}

