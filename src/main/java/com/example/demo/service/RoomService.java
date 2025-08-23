package com.example.demo.service;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.entity.Room;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO room);
    List<RoomDTO> listRoomsForUser(Long userId);
    RoomDTO findById(Long id);
}
