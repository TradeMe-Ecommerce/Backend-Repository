package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        if (dto.getHistoryId() == null) {
            throw new RuntimeException("History ID is required");
        }
        int num = 0;
        if (!findByHistoryId(dto.getHistoryId()).isEmpty()) {
            num = findByHistoryId(dto.getHistoryId()).size();
        }
        dto.setOrderNumber(num + 1);
        dto.setStatus("PENDING");
        return orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(dto)));
    }

    @Override
    public List<OrderDTO> findByHistoryId(Long historyId) {
        return orderRepository.findByHistory_Id(historyId)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    public OrderDTO updateOrder(OrderDTO dto) {
        Order order = orderRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        try {
            order.setStatus(OrderStatus.valueOf(dto.getStatus().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Estado de orden inválido: " + dto.getStatus());
        }
        order.setOrderNumber(dto.getOrderNumber());
        order.setTransactions(dto.getTransactionIds().stream().map(
                transactionId -> transactionRepository.findById(transactionId)
                        .orElseThrow(() -> new RuntimeException("Transaction not found: " + transactionId))
                ).collect(Collectors.toSet())
        );
        order.setDate(DateFormatter.convertStringToDate(dto.getDate()));

        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO findByDateAndUser(String date, long userId) {
        Order order = orderRepository.findByDate(DateFormatter.convertStringToDate(date)).stream().
                filter(o -> o.getHistory().getUser().getId() == userId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found for date: " + date + " and user ID: " + userId));
        return orderMapper.toDTO(order);
    }


    @Override
    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

}

