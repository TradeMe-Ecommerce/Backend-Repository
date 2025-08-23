package com.example.demo.controller;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.entity.Message;
import com.example.demo.entity.Room;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.MessageService;
import com.example.demo.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageRepository messageRepository;


    @PostMapping
    @PreAuthorize("@roomSecurity.hasAccess(#dto.roomId, authentication)")
    public ResponseEntity<MessageDTO> create(@RequestBody MessageDTO dto) {
        MessageDTO saved = messageService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);   // 201
    }

    @GetMapping("/{id}")
    public MessageDTO byId(@PathVariable Long id) {                    // 200
        return messageService.findById(id);
    }

    @PatchMapping
    public MessageDTO update(@RequestBody MessageDTO dto) {            // 200
        return messageService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {        // 204
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<?>> getMessagesForRoom(@PathVariable Long roomId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        boolean isParticipant = room.getUser().getId() == (currentUserId) || room.getPeerUser().getId() == (currentUserId);
        if (!isParticipant) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not a member of this room");
        }

        List<Message> messages = messageRepository.findByRoom_IdOrderByDateAsc(roomId);

        List<MessageDTO> messageDTOs = messages.stream()
                .map(message -> messageMapper.toDTO(message))
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageDTOs);
    }
}
