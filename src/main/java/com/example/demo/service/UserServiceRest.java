package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.DTO.InventoryDTO;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;

public interface UserServiceRest {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user);
    void deleteUser(Long id);
    void addInventoryToUser(Long userId, Long productId, int stock);
    void removeInventoryFromUser(Long userId, Long productId);
    void updateInventoryForUser(Long userId, Long productId, int stock);
    List<UserDTO> searchUsers(String query);
    Set<InventoryDTO> getUserInventories(Long userId);
    List<InventoryDTO> getInventoriesByProductId(Long productId);
    User registerUser(RegisterRequest registerRequest);
}
