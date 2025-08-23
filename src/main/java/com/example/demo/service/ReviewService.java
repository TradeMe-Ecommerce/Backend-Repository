package com.example.demo.service;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.entity.Review;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO review);
    ReviewDTO findByTransaction(Long transactionId);
    ReviewDTO updateReview(ReviewDTO review);
    void deleteReview(Long id);

    ReviewDTO getReviewById(Long id);

    List<ReviewDTO> findAll();
}
