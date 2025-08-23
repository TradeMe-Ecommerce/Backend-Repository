package com.example.demo.mapper;

import com.example.demo.DTO.HistoryDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-23T18:02:36-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class HistoryMapperImpl implements HistoryMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public HistoryDTO toDTO(History history) {
        if ( history == null ) {
            return null;
        }

        HistoryDTO historyDTO = new HistoryDTO();

        historyDTO.setUserId( historyUserId( history ) );
        historyDTO.setId( history.getId() );
        historyDTO.setOrders( orderSetToOrderDTOSet( history.getOrders() ) );

        return historyDTO;
    }

    @Override
    public History toEntity(HistoryDTO historyDTO) {
        if ( historyDTO == null ) {
            return null;
        }

        History history = new History();

        history.setUser( historyDTOToUser( historyDTO ) );
        history.setId( historyDTO.getId() );
        history.setOrders( orderDTOSetToOrderSet( historyDTO.getOrders() ) );

        return history;
    }

    private long historyUserId(History history) {
        if ( history == null ) {
            return 0L;
        }
        User user = history.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    protected Set<OrderDTO> orderSetToOrderDTOSet(Set<Order> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderDTO> set1 = new LinkedHashSet<OrderDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Order order : set ) {
            set1.add( orderMapper.toDTO( order ) );
        }

        return set1;
    }

    protected User historyDTOToUser(HistoryDTO historyDTO) {
        if ( historyDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( historyDTO.getUserId() );

        return user;
    }

    protected Set<Order> orderDTOSetToOrderSet(Set<OrderDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Order> set1 = new LinkedHashSet<Order>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderDTO orderDTO : set ) {
            set1.add( orderMapper.toEntity( orderDTO ) );
        }

        return set1;
    }
}
