package com.example.demo.controller;
import com.example.demo.DTO.RoomDTO;
import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.impl.RoomServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO req) {

        RoomDTO dto = roomService.createRoom(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id " + "|| hasRole('ADMIN')")
    public List<RoomDTO> listRoomsForUser(@PathVariable Long userId) {
        return roomService.listRoomsForUser(userId);
    }


    @GetMapping("/{id}")
    @PreAuthorize("#userId == authentication.principal.id " + "|| hasRole('ADMIN')")
    public RoomDTO findById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRoomsForCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        long currentUserId = userDetails.getId();
        User currentUser = userRepository.findById(currentUserId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Room> rooms = roomRepository.findByUserOrPeerUser(currentUser, currentUser);

        List<RoomDTO> roomDTOs = rooms.stream().map(room -> {
            RoomDTO dto = new RoomDTO();
            dto.setId(room.getId());

            User otherUser = room.getUser().getId() == currentUserId
                    ? room.getPeerUser()
                    : room.getUser();

            dto.setPeerUserId(otherUser.getId());
            dto.setPeerUserName(otherUser.getName());

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(roomDTOs);
    }
}
