package com.example.demo.service.impl;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.entity.Message;
import com.example.demo.entity.Room;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import com.example.demo.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service @Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private  MessageMapper messageMapper;

    @Override
    public MessageDTO create(MessageDTO dto) {

        if (dto == null || dto.getRoomId() == 0 || dto.getMessage() == null)
            throw new BadRequestException("roomId y message son obligatorios");

        Room room = messageRepository.findById(dto.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room " + dto.getRoomId() + " no existe")).getRoom();

        Message entity = messageMapper.toEntity(dto);
        entity.setRoom(room);
        entity.setDate(dto.getDate() == null ? new Date() : DateFormatter.convertStringToDate(dto.getDate()));

        return messageMapper.toDTO(messageRepository.save(entity));
    }

    @Override
    public List<MessageDTO> findAllByRoom(Long roomId) {
        return messageRepository.findByRoom_IdOrderByDateAsc(roomId)
                .stream()
                .map(messageMapper::toDTO)
                .toList();
    }

    @Override
    public MessageDTO findById(Long id) {
        return messageRepository.findById(id)
                .map(messageMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mensaje " + id + " no existe"));
    }

    @Override
    public MessageDTO update(MessageDTO dto) {

        if (dto == null || dto.getId() == 0)
            throw new BadRequestException("id del mensaje es obligatorio");

        Message existing = messageRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mensaje " + dto.getId() + " no existe"));

        if (dto.getMessage() != null) existing.setMessage(dto.getMessage());
        if (dto.getDate()    != null) existing.setDate(DateFormatter.convertStringToDate(dto.getDate()));

        return messageMapper.toDTO(messageRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Message msg = messageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mensaje " + id + " no existe"));
        messageRepository.delete(msg);
    }
}

