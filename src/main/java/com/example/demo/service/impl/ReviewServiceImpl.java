package com.example.demo.service.impl;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.entity.Review;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.ReviewService;
import com.example.demo.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ReviewMapper mapper;

    @Autowired
    private TransactionRepository txRepo;


    @Override
    public ReviewDTO createReview(ReviewDTO dto) {

        if (dto == null || dto.getTransactionId() == 0)
            throw new BadRequestException("transactionId es obligatorio");

        // ¿ya existe review para esa transacción?
        if (reviewRepo.findByTransaction_Id(dto.getTransactionId()).isPresent())
            throw new BadRequestException(
                    "La transacción " + dto.getTransactionId()
                            + " ya tiene un review asociado");

        Transaction tx = txRepo.findById(dto.getTransactionId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Transacción " + dto.getTransactionId() + " no existe"));

        Review entity = mapper.toEntity(dto);
        entity.setTransaction(tx);

        return mapper.toDTO(reviewRepo.save(entity));
    }

    @Override
    public ReviewDTO findByTransaction(Long txId) {
        return reviewRepo.findByTransaction_Id(txId)
                .map(mapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No hay review para la transacción " + txId));
    }
    @Override
    public ReviewDTO updateReview(ReviewDTO dto) {

        if (dto == null || dto.getId() == 0)
            throw new BadRequestException("id del review es obligatorio");

        Review existing = reviewRepo.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review " + dto.getId() + " no existe"));

        existing.setPoints(dto.getPoints());
        existing.setDescription(dto.getDescription());
        existing.setDate(DateFormatter.convertStringToDate(dto.getDate()));

        return mapper.toDTO(reviewRepo.save(existing));
    }

    @Override
    public void deleteReview(Long id) {
        Review rev = reviewRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review " + id + " no existe"));
        reviewRepo.delete(rev);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        return reviewRepo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review " + id + " no existe"));
    }


    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();   // 200 – lista vacía si no hay datos
    }
}
