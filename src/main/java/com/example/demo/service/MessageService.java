package com.example.demo.service;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.entity.Message;

import java.util.List;

public interface MessageService {
    MessageDTO create(MessageDTO dto);

    List<MessageDTO> findAllByRoom(Long roomId);

    MessageDTO findById(Long id);

    MessageDTO update(MessageDTO dto);

    void delete(Long id);
}
