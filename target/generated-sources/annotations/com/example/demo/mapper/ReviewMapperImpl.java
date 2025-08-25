package com.example.demo.mapper;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.entity.Review;
import com.example.demo.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T21:13:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDTO toDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setTransactionId( reviewTransactionId( review ) );
        reviewDTO.setDate( convertDateToString( review.getDate() ) );
        reviewDTO.setId( review.getId() );
        reviewDTO.setPoints( review.getPoints() );
        reviewDTO.setDescription( review.getDescription() );

        return reviewDTO;
    }

    @Override
    public Review toEntity(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setTransaction( reviewDTOToTransaction( reviewDTO ) );
        review.setDate( convertStringToDate( reviewDTO.getDate() ) );
        review.setId( reviewDTO.getId() );
        review.setPoints( reviewDTO.getPoints() );
        review.setDescription( reviewDTO.getDescription() );

        return review;
    }

    private long reviewTransactionId(Review review) {
        if ( review == null ) {
            return 0L;
        }
        Transaction transaction = review.getTransaction();
        if ( transaction == null ) {
            return 0L;
        }
        long id = transaction.getId();
        return id;
    }

    protected Transaction reviewDTOToTransaction(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId( reviewDTO.getTransactionId() );

        return transaction;
    }
}
