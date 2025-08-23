package com.example.demo.mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.util.DateFormatter;
import com.example.demo.util.TransactionMapperHelper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = TransactionMapperHelper.class)
public interface OrderMapper {

    @Mapping(source = "history.id", target = "historyId")
    @Mapping(source = "transactions", target = "transactionIds")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    OrderDTO toDTO(Order order);

    @Mapping(source = "historyId", target = "history.id")
    @Mapping(source = "transactionIds", target = "transactions")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertStringToDate")
    Order toEntity(OrderDTO dto);

    @Named("convertStringToDate")
    default Date convertStringToDate(String dateString) {
        return DateFormatter.convertStringToDate(dateString);
    }

    @Named("convertDateToString")
    default String convertDateToString(Date date) {
        return DateFormatter.convertDateToString(date);
    }
}

