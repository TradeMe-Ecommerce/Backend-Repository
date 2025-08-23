package com.example.demo.service;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> findAll();
    TransactionDTO findById(Long id);
    TransactionDTO createTransaction(TransactionDTO transactionDTO, long buyerId, String date);
    TransactionDTO updateTransaction(TransactionDTO transaction);
    void deleteTransaction(Long id);

    List<TransactionDTO> findByUserId(Long id);
}
