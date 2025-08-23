package com.example.demo.service.impl;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.RoomDTO;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;


    public RoomDTO createRoom(RoomDTO room) {
        try {
            userService.findById(room.getUserId());
            userService.findById(room.getPeerUserId());
            if (room.getUserId() == room.getPeerUserId()) {
                throw new RuntimeException("Cannot create a room with the same user as both participants");
            } else if (roomRepository.existsByUserIdAndPeerUserId(room.getUserId(), room.getPeerUserId())) {
                throw new RuntimeException("Room already exists between these users");
            }
            return roomMapper.toDTO(
                    roomRepository.save(roomMapper.toEntity(room))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<RoomDTO> listRoomsForUser(Long userId) {
        return roomRepository.findByUserId(userId).stream().map(room -> {
            RoomDTO dto = roomMapper.toDTO(room);
            dto.setUserId(room.getUser().getId());
            dto.setUserName(room.getUser().getName());
            dto.setPeerUserId(room.getPeerUser().getId());
            dto.setPeerUserName(room.getPeerUser().getName());
            dto.setMessages(messageService.findAllByRoom(room.getId()));
            return dto;
        }).toList();
    }

    @Override
    public RoomDTO findById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        RoomDTO dto = roomMapper.toDTO(room);

        dto.setUserId(room.getUser().getId());
        dto.setUserName(room.getUser().getName());
        dto.setPeerUserId(room.getPeerUser().getId());
        dto.setPeerUserName(room.getPeerUser().getName());

        List<MessageDTO> msgs = messageService.findAllByRoom(id);
        dto.setMessages(msgs);
        return dto;
    }
}
