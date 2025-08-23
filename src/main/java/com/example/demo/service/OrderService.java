package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.OrderStatus;

public interface OrderService {
    OrderDTO findById(Long id);
    OrderDTO createOrder(OrderDTO order);
    OrderDTO updateOrder(OrderDTO order);
    void deleteOrder(Long id);
    List<OrderDTO> findByHistoryId(Long historyId);
    OrderDTO updateOrderStatus(Long id, OrderStatus status);
    List<OrderDTO> findAll();
    OrderDTO findByDateAndUser(String date, long userId);
}
