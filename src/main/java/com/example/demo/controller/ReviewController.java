package com.example.demo.controller;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.service.ReviewService;

import com.example.demo.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO dto) {
        dto.setDate(DateFormatter.convertDateToString(new Date()));
        ReviewDTO saved = reviewService.createReview(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping("/transaction/{txId}")
    public ResponseEntity<ReviewDTO> findByTransaction(@PathVariable Long txId) {
        try {
            ReviewDTO review = reviewService.findByTransaction(txId);
            return ResponseEntity.ok(review);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<ReviewDTO> update(@RequestBody ReviewDTO dto) {
        try {
            ReviewDTO updated = reviewService.updateReview(dto);
            dto.setDate(DateFormatter.convertDateToString(new Date()));
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAll() {
        List<ReviewDTO> reviews = reviewService.findAll();
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }
}
