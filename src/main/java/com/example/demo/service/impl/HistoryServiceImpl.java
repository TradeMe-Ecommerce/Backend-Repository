package com.example.demo.service.impl;

import com.example.demo.DTO.HistoryDTO;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.HistoryMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepo;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepo;

    @Override
    public HistoryDTO create(HistoryDTO dto) {

        if (dto == null || dto.getUserId() == 0)
            throw new BadRequestException("userId es obligatorio");

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario " + dto.getUserId() + " no existe"));

        if (historyRepo.findByUser_Id(user.getId()).isPresent())
            throw new BadRequestException(
                    "El usuario " + user.getId() + " ya tiene un historial");

        History entity = historyMapper.toEntity(dto);
        entity.setUser(user);

        return historyMapper.toDTO(historyRepo.save(entity));
    }

    @Override public List<HistoryDTO> findAll() {
        return historyRepo.findAll()
                .stream()
                .map(historyMapper::toDTO)
                .toList();
    }

    @Override public HistoryDTO findById(Long id) {
        return historyRepo.findById(id)
                .map(historyMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Historial " + id + " no existe"));
    }

    @Override public HistoryDTO findByUser(Long userId) {
        return historyRepo.findByUser_Id(userId)
                .map(historyMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El usuario " + userId + " no tiene historial"));
    }

    @Override public HistoryDTO update(HistoryDTO dto) {

        if (dto == null || dto.getId() == 0)
            throw new BadRequestException("id del historial es obligatorio");

        History existing = historyRepo.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Historial " + dto.getId() + " no existe"));

        if (dto.getUserId() == 0 && dto.getUserId() != existing.getUser().getId()) {
            throw new BadRequestException("userId es obligatorio y no puede cambiarse");
        }

        existing.setOrders(
                dto.getOrders().stream()
                        .map(orderDTO -> {
                            Order order = orderRepository.findById(orderDTO.getId())
                                    .orElseThrow(() -> new ResourceNotFoundException("Order " + orderDTO.getId() + " no existe"));

                            if (order.getStatus() == OrderStatus.CANCELLED) {
                                throw new IllegalArgumentException("La orden " + order.getId() + " está cancelada");
                            } else if (order.getHistory().getId() != existing.getId()) {
                                throw new IllegalArgumentException("La orden " + order.getId() + " no pertenece a este historial");
                            }
                            return order;
                        })
                        .collect(Collectors.toSet())
        );

        return historyMapper.toDTO(historyRepo.save(existing));
    }


    @Override public void delete(Long id) {
        History h = historyRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Historial " + id + " no existe"));
        historyRepo.delete(h);
    }
}

