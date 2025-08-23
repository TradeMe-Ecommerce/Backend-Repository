package com.example.demo.mapper;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.entity.Transaction;
import com.example.demo.util.DateFormatter;
import com.example.demo.util.ProductMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    TransactionDTO toDTO(Transaction transaction);


    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "orderId", target = "order.id")
    Transaction toEntity(TransactionDTO transactionDTO);




}
