package com.example.demo.mapper;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.entity.Review;
import com.example.demo.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    ReviewDTO toDTO(Review review);

    @Mapping(source = "transactionId", target = "transaction.id")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertStringToDate")
    Review toEntity(ReviewDTO reviewDTO);

    @Named("convertStringToDate")
    default Date convertStringToDate(String dateString) {
        return DateFormatter.convertStringToDate(dateString);
    }

    @Named("convertDateToString")
    default String convertDateToString(Date date) {
        return DateFormatter.convertDateToString(date);
    }
}
