package com.example.demo.service;

import com.example.demo.DTO.HistoryDTO;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;

import java.util.List;

public interface HistoryService {
    HistoryDTO create(HistoryDTO dto);
    List<HistoryDTO> findAll();
    HistoryDTO findById(Long id);
    HistoryDTO findByUser(Long userId);
    HistoryDTO update(HistoryDTO dto);
    void delete(Long id);
}
