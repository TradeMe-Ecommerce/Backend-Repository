package com.example.demo.util;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TransactionMapperHelper {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Long> mapTransactionsToIds(Set<Transaction> transactions) {
        if (transactions == null) {
            return null;
        }
        return transactions.stream()
                .map(Transaction::getId)
                .collect(Collectors.toList());
    }

    public Set<Transaction> mapIdsToTransactions(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> transactionRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("Transaction not found with id: " + id)))
                .collect(Collectors.toSet());
    }
}
