package com.example.demo.mapper;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.*;
import com.example.demo.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    ProductDTO toDto(Product product);

    @Mapping(source = "date", target = "date", qualifiedByName = "convertStringToDate")
    Product toEntity(ProductDTO productDTO);


    @Named("convertStringToDate")
    default Date convertStringToDate(String dateString) {
        return DateFormatter.convertStringToDate(dateString);
    }

    @Named("convertDateToString")
    default String convertDateToString(Date date) {
        return DateFormatter.convertDateToString(date);
    }
}
