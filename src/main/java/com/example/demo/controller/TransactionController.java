package com.example.demo.controller;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.service.TransactionService;

import com.example.demo.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<TransactionDTO> transactions = transactionService.findAll();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            TransactionDTO transaction = transactionService.findById(id);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id) {
        try {
            List<TransactionDTO> transaction = transactionService.findByUserId(id);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/{buyerId}")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transaction, @PathVariable long buyerId) {
        try {
            Date currentDate = new Date();
            TransactionDTO createdTransaction = transactionService.createTransaction(transaction, buyerId, DateFormatter.convertDateToString(currentDate));
            return ResponseEntity.status(201).body(createdTransaction);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDTO transaction) {
        try {
            TransactionDTO updatedTransaction = transactionService.updateTransaction(transaction);
            return ResponseEntity.ok(updatedTransaction);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
