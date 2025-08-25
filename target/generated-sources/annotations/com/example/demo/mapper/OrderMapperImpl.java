package com.example.demo.mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.util.TransactionMapperHelper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T20:14:07-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private TransactionMapperHelper transactionMapperHelper;

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setHistoryId( orderHistoryId( order ) );
        orderDTO.setTransactionIds( transactionMapperHelper.mapTransactionsToIds( order.getTransactions() ) );
        orderDTO.setDate( convertDateToString( order.getDate() ) );
        orderDTO.setId( order.getId() );
        orderDTO.setOrderNumber( order.getOrderNumber() );
        if ( order.getStatus() != null ) {
            orderDTO.setStatus( order.getStatus().name() );
        }

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setHistory( orderDTOToHistory( dto ) );
        order.setTransactions( transactionMapperHelper.mapIdsToTransactions( dto.getTransactionIds() ) );
        order.setDate( convertStringToDate( dto.getDate() ) );
        order.setId( dto.getId() );
        order.setOrderNumber( dto.getOrderNumber() );
        if ( dto.getStatus() != null ) {
            order.setStatus( Enum.valueOf( OrderStatus.class, dto.getStatus() ) );
        }

        return order;
    }

    private Long orderHistoryId(Order order) {
        if ( order == null ) {
            return null;
        }
        History history = order.getHistory();
        if ( history == null ) {
            return null;
        }
        long id = history.getId();
        return id;
    }

    protected History orderDTOToHistory(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        History history = new History();

        if ( orderDTO.getHistoryId() != null ) {
            history.setId( orderDTO.getHistoryId() );
        }

        return history;
    }
}
